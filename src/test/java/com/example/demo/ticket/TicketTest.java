package com.example.demo.ticket;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

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
}