package com.myticket;

import java.util.Objects;

public class Customer {
    private Bag bag;

    public Customer(Bag bag) {
        Objects.requireNonNull(bag, "bag must not be null");

        this.bag = bag;
    }

    /**
     * 티켓 구매
     * @param ticket 구매하려는 티켓
     * @return 구매 후 잔액
     */
    public Money buy(Ticket ticket) {
        Objects.requireNonNull(ticket, "ticket must not be null");
        return bag.hold(ticket);
    }
}
