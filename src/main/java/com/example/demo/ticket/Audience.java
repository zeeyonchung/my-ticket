package com.example.demo.ticket;

import lombok.Getter;

@Getter
public class Audience {
    private Bag bag;

    public Audience(Bag bag) {
        this.bag = bag;
    }
}
