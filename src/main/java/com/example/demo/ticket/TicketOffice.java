package com.example.demo.ticket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TicketOffice {

    private Money amount;
    private List<Ticket> tickets = new ArrayList<>();

    public TicketOffice(Money amount, Ticket... tickets) {
        this.amount = amount;
        this.tickets.addAll(Arrays.asList(tickets));
    }

    public Ticket getTicket() {
        return tickets.remove(0);
    }

    private void minusAmount(Money amount) {
        this.amount = this.amount.minus(amount);
    }

    private void plusAmount(Money amount) {
        this.amount = this.amount.plus(amount);
    }

    public void sellTicketTo(Audience audience) {
        plusAmount(audience.buy(getTicket()));
    }
}
