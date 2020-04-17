package com.myticket;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.DateTimeException;
import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ScreeningTest {

    private TestClock testClock = new TestClock();

    @Mock
    private Movie movieMock;

    @AfterEach
    void resetClock() {
        Clock.reset();
    }

    @Nested
    @DisplayName("객체 생성")
    class NewScreeningTest {

        @Test
        @DisplayName("movie가 null이면 NullPointerException")
        void createWithNullMovie_Then_throwNullPointerException() {
            LocalDateTime screenedAt = LocalDateTime.of(2020, 4, 15, 10, 15);

            assertThrows(NullPointerException.class,
                    () -> new Screening(null, screenedAt));
        }

        @Test
        @DisplayName("screenedAt이 null이면 NullPointerException")
        void createdWithNullScreenedAt_Then_ThrowNullPointerException() {
            assertThrows(NullPointerException.class,
                    () -> new Screening(movieMock, null));
        }

        @Test
        @DisplayName("지나간 시간의 상영을 생성하면 DateTimeException")
        void createLaterThanNow_Then_throwDateTimeException() {
            testClock.setNow(LocalDateTime.of(2020, 4, 15, 10, 16));
            LocalDateTime screenedAt = LocalDateTime.of(2020, 4, 15, 10, 15);

            assertThrows(DateTimeException.class,
                    () -> new Screening(movieMock, screenedAt));
        }
    }

    @Nested
    @DisplayName("상영 예매")
    class ReserveTest {

        @Test
        @DisplayName("상영 시작 시간이 지나고 예매하면 DateTimeException")
        void reserveLaterThanScreenTime_Then_throwDateTimeException() {
            testClock.setNow(LocalDateTime.of(2020, 4, 15, 10, 14));
            Screening screening = new Screening(movieMock, LocalDateTime.of(2020, 4, 15, 10, 15));
            testClock.setNow(LocalDateTime.of(2020, 4, 15, 10, 16));

            assertThrows(DateTimeException.class,
                    () -> screening.reserve( 1));
        }

        @Test
        @DisplayName("예매 가격은 관람객 수에 비례한다")
        void reserveMoreThan1Audience_Then_FeeIsMultiplyOfMovieFee() {
            given(movieMock.getFee()).willReturn(Money.wons(5000));
            testClock.setNow(LocalDateTime.of(2020, 4, 15, 10, 14));
            Screening screening = new Screening(movieMock, LocalDateTime.of(2020, 4, 15, 10, 15));

            Ticket ticket1 = screening.reserve(2);
            assertEquals(Money.wons(10000), ticket1.getFee());

            Ticket ticket2 = screening.reserve(100);
            assertEquals(Money.wons(500000), ticket2.getFee());
        }

        @Test
        @DisplayName("영화 가격이 0원이면 예매 가격은 0원이다")
        void reserveMovieWithZeroFee_Then_FeeIsZero() {
            given(movieMock.getFee()).willReturn(Money.ZERO);
            testClock.setNow(LocalDateTime.of(2020, 4, 15, 10, 14));
            Screening screening = new Screening(movieMock, LocalDateTime.of(2020, 4, 15, 10, 15));

            Ticket ticket1 = screening.reserve(2);
            assertEquals(Money.ZERO, ticket1.getFee());

            Ticket ticket2 = screening.reserve(100);
            assertEquals(Money.ZERO, ticket2.getFee());
        }
    }

    @Nested
    @DisplayName("상영 시간")
    class RunningTimeTest {

        @Test
        @DisplayName("시작 시간에 상영 시간을 더한 시간이 지나지 않았으면 상영이 끝나지 않았다")
        void startTimePlusRunningTimeIsSameAsNow_Then_Finished() {
            given(movieMock.getRunningTime()).willReturn(Duration.ofMinutes(117));
            testClock.setNow(LocalDateTime.of(2020, 4, 10, 10, 0));
            Screening screening = new Screening(movieMock, LocalDateTime.of(2020, 4, 12, 12, 50));
            testClock.setNow(LocalDateTime.of(2020, 4, 12, 14, 47));

            boolean finished = screening.isFinished();
            assertFalse(finished);
        }

        @Test
        @DisplayName("시작 시간에 상영 시간을 더한 시간이 지났으면 상영이 끝났다")
        void startTimePlusRunningTimeIsGreaterThanNow_Then_Finished() {
            given(movieMock.getRunningTime()).willReturn(Duration.ofMinutes(117));
            testClock.setNow(LocalDateTime.of(2020, 4, 10, 10, 0));
            Screening screening = new Screening(movieMock, LocalDateTime.of(2020, 4, 12, 12, 50));
            testClock.setNow(LocalDateTime.of(2020, 4, 12, 15, 0));

            boolean finished = screening.isFinished();
            assertTrue(finished);
        }
    }
}