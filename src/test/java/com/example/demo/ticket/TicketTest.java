package com.example.demo.ticket;

import com.example.demo.ticket.exception.TicketAlreadyUsedException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.DateTimeException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class TicketTest {

    @Mock
    private Screening screeningMock;

    @Nested
    @DisplayName("객체 생성")
    class NewTicketTest {

        @Test
        @DisplayName("screening이 null이면 NullPointerException")
        void createdWithNullScreening_Then_throwNullPointerException() {
            assertThrows(NullPointerException.class,
                    () -> new Ticket(null, Money.ZERO, 1));
        }

        @Test
        @DisplayName("fee가 null이면 NullPointerException")
        void createdWithNullFee_Then_throwNullPointerException() {
            assertThrows(NullPointerException.class,
                    () -> new Ticket(screeningMock, null, 1));
        }

        @Test
        @DisplayName("관람객이 0명 이하이면 IllegalArgumentException")
        void createdLessThan1Audience_Then_throwIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class,
                    () -> new Ticket(screeningMock, Money.ZERO, 0));
        }
    }

    @Nested
    @DisplayName("티켓 사용")
    class UseTicketTest {

        @Test
        @DisplayName("이미 사용된 티켓을 사용하면 TicketAlreadyUsedException")
        void useUsedTicket_Then_ThrowTicketAlreadyUsedException() {
            Ticket ticket = new Ticket(screeningMock, Money.ZERO, 1);
            ticket.use();

            assertThrows(TicketAlreadyUsedException.class, ticket::use);
        }

        @Test
        @DisplayName("이미 상영이 끝난 티켓을 사용하면 DateTimeException")
        void useScreeningFinishedTicket_Then_ThrowDateTimeException() {
            given(screeningMock.isFinished()).willReturn(true);
            Ticket ticket = new Ticket(screeningMock, Money.ZERO, 1);

            assertThrows(DateTimeException.class, ticket::use);
        }
    }
}