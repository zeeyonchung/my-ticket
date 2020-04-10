package com.example.demo.ticket;

public class Audience {
    private Bag bag;

    public Audience(Bag bag) {
        this.bag = bag;
    }

    public Money buy(Ticket ticket) {
        return bag.hold(ticket);
    }
}
