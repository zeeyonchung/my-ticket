package com.example.demo.ticket;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TicketOffice {

    private BigDecimal amount;
    private List<Ticket> tickets = new ArrayList<>();

    public TicketOffice(BigDecimal amount, Ticket... tickets) {
        this.amount = amount;
        this.tickets.addAll(Arrays.asList(tickets));
    }

    public Ticket getTicket() {
        return tickets.remove(0);
    }

    private void minusAmount(BigDecimal amount) {
        this.amount = this.amount.subtract(amount);
    }

    private void plusAmount(BigDecimal amount) {
        this.amount = this.amount.add(amount);
    }

    public void sellTicketTo(Audience audience) {
        plusAmount(audience.buy(getTicket()));
    }
}
