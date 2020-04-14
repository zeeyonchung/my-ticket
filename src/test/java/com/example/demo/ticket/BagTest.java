package com.example.demo.ticket;

import com.example.demo.ticket.exception.NotEnoughBalanceException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BagTest {

    @Nested
    @DisplayName("객체 생성")
    class NewBagTest {

        @Test
        @DisplayName("amount가 null이면 NullPointerException")
        void createdWithNullAmount_Then_ThrowNullPointerException() {
            assertThrows(NullPointerException.class,
                    () -> new Bag(null));
        }
    }

    @Nested
    @DisplayName("티켓 구매")
    class HoldTicketTest {

        @Mock
        private Screening screeningMock;

        @Test
        @DisplayName("ticket이 null이면 NullPointerException")
        void holdNullTicket_Than_ThrowNullPointerException() {
            Bag bag = new Bag(Money.wons(10000));

            assertThrows(NullPointerException.class,
                    () -> bag.hold(null));
        }

        @Test
        @DisplayName("가진 돈이 티켓 가격보다 적으면 NotEnoughBalanceException")
        void amountIsLessThanTicketFee_Than_ThrowNotEnoughBalanceException() {
            Bag bag = new Bag(Money.wons(1000));
            Ticket ticket = new Ticket(screeningMock, Money.wons(2000), 1);

            assertThrows(NotEnoughBalanceException.class,
                    () -> bag.hold(ticket));
        }

        @Test
        @DisplayName("티켓 가격만큼 잔액이 차감된다")
        void hold_Then_AmountIsDeductedByTicketFee() {
            Bag bag = new Bag(Money.wons(10000));
            Ticket ticket = new Ticket(screeningMock, Money.wons(2000), 1);
            Money balance = bag.hold(ticket);

            assertEquals(Money.wons(8000), balance);
        }

        @Test
        @DisplayName("보유 티켓이 한 장 늘어난다")
        void hold_Then_OneTicketAdded() {
            Bag bag = new Bag(Money.wons(10000));
            Ticket ticket = new Ticket(screeningMock, Money.wons(2000), 1);
            bag.hold(ticket);

            assertEquals(1, bag.getTicketSize());
        }
    }
}