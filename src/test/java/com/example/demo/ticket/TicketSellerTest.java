package com.example.demo.ticket;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class TicketSellerTest {

    private TestClock testClock = new TestClock();

    @Nested
    @DisplayName("티켓 판매")
    class SellTicketTest {

        private TicketSellRequest createRequest() {
            testClock.setNow(LocalDateTime.of(2020, 4, 9, 12, 0));

            Customer customer = new Customer(new Bag(Money.wons(10000)));
            Screening screening = new Screening(new Movie("movie", Duration.ofMinutes(60), Money.wons(2000)),
                    LocalDateTime.of(2020, 4, 10, 12, 0));

            return TicketSellRequest.builder()
                    .customer(customer)
                    .screening(screening)
                    .audienceCount(2)
                    .build();
        }

        @Test
        @DisplayName("판매된 티켓을 반환한다")
        void sell_Then_NewTicketIsNotNull() {
            TicketSellRequest sellRequest = createRequest();

            TicketSeller ticketSeller = new TicketSeller();
            Ticket ticket = ticketSeller.sell(sellRequest);
            assertNotNull(ticket);
        }

    }

}
