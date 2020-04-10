package com.example.demo.ticket;

import java.math.BigDecimal;

public class Bag {
    private Money amount;
    private Invitation invitation;
    private Ticket ticket;

    public Bag(Money amount) {
        this.amount = amount;
    }

    public Bag(Money amount, Invitation invitation) {
        this.amount = amount;
        this.invitation = invitation;
    }

    private boolean hasInvitation() {
        return invitation != null;
    }

    private boolean hasTicket() {
        return ticket != null;
    }

    private void minusAmount(Money amount) {
        this.amount = this.amount.minus(amount);
    }

    private void plusAmount(Money amount) {
        this.amount = this.amount.plus(amount);
    }

    public Money hold(Ticket ticket) {
        this.ticket = ticket;

        if (!hasInvitation()) {
            minusAmount(ticket.getFee());
            return ticket.getFee();
        }

        return Money.ZERO;
    }
}
