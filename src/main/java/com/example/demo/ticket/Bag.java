package com.example.demo.ticket;

import java.math.BigDecimal;

public class Bag {
    private BigDecimal amount;
    private Invitation invitation;
    private Ticket ticket;

    public Bag(BigDecimal amount) {
        this.amount = amount;
    }

    public Bag(BigDecimal amount, Invitation invitation) {
        this.amount = amount;
        this.invitation = invitation;
    }

    private boolean hasInvitation() {
        return invitation != null;
    }

    private boolean hasTicket() {
        return ticket != null;
    }

    private void minusAmount(BigDecimal amount) {
        this.amount = this.amount.subtract(amount);
    }

    private void plusAmount(BigDecimal amount) {
        this.amount = this.amount.add(amount);
    }

    public BigDecimal hold(Ticket ticket) {
        this.ticket = ticket;

        if (!hasInvitation()) {
            minusAmount(ticket.getFee());
            return ticket.getFee();
        }

        return BigDecimal.ZERO;
    }
}
