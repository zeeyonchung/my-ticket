package com.myticket;

import com.myticket.Bag;
import com.myticket.Customer;
import com.myticket.Money;
import com.myticket.Ticket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CustomerTest {

    @Nested
    @DisplayName("객체 생성")
    class NewCustomerTest {

        @Test
        @DisplayName("bag이 null이면 NullPointerException")
        void createdWithNullBag_Then_ThrowNullPointerException() {
            assertThrows(NullPointerException.class,
                    () -> new Customer(null));
        }
    }

    @Nested
    @DisplayName("티켓 구매")
    class TicketBuyTest {

        @Mock
        private Ticket ticket;

        @Test
        @DisplayName("ticket이 null이면 NullPointerException")
        void buyNullTicket_Than_ThrowNullPointerException() {
            Bag bag = new Bag(Money.wons(10000));
            Customer customer = new Customer(bag);

            assertThrows(NullPointerException.class,
                    () -> customer.buy(null));
        }

        @Test
        @DisplayName("티켓 가격만큼 잔액이 차감된다")
        void buy_Then_AmountIsDeductedByTicketFee() {
            given(ticket.getFee()).willReturn(Money.wons(1500));
            Bag bag = new Bag(Money.wons(10000));
            Customer customer = new Customer(bag);
            Money balance = customer.buy(ticket);

            assertEquals(Money.wons(8500), balance);
        }
    }
}