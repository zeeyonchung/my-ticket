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

    public boolean hasInvitation() {
        return invitation != null;
    }

    public boolean hasTicket() {
        return ticket != null;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public void minusAmount(BigDecimal amount) {
        this.amount = this.amount.subtract(amount);
    }

    public void plusAmount(BigDecimal amount) {
        this.amount = this.amount.add(amount);
    }
}
