package com.example.demo.ticket;

import java.util.Objects;

public class Ticket {
    private Screening screening;
    private Money fee;
    private int audienceCount;

    public Ticket(Screening screening, Money fee, int audienceCount) {
        Objects.requireNonNull(screening, "screening must not be null");
        Objects.requireNonNull(fee, "fee must not be null");

        if (audienceCount < 1) {
            throw new IllegalArgumentException("관람객은 1명 이상이어야 합니다.");
        }

        this.screening = screening;
        this.fee = fee;
        this.audienceCount = audienceCount;
    }

    public Money getFee() {
        return fee;
    }
}
