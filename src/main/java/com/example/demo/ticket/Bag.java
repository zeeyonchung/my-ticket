package com.example.demo.ticket;

import com.example.demo.ticket.exception.NotEnoughBalanceException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Bag {
    private Money amount;
    private List<Ticket> tickets = new ArrayList<>();

    public Bag(Money amount) {
        Objects.requireNonNull(amount, "amount must not be null");

        this.amount = amount;
    }

    private void minusAmount(Money amount) {
        this.amount = this.amount.minus(amount);
    }

    private void plusAmount(Money amount) {
        this.amount = this.amount.plus(amount);
    }

    /**
     * 티켓 구매
     * @param ticket 구매하려는 티켓
     * @return 구매 후 잔액
     */
    public Money hold(Ticket ticket) {
        if (amount.isLessThan(ticket.getFee())) {
            throw new NotEnoughBalanceException();
        }

        tickets.add(ticket);
        minusAmount(ticket.getFee());
        return amount;
    }
}
