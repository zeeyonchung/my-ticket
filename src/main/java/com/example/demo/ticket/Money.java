package com.example.demo.ticket;

import java.math.BigDecimal;

public class Money {
    public static final Money ZERO = Money.wons(BigDecimal.ZERO);

    private final BigDecimal amount;

    Money(BigDecimal amount) {
        this.amount = amount;
    }

    public static Money wons(BigDecimal amount) {
        return new Money(amount);
    }

    public Money plus(Money amount) {
        return new Money(this.amount.add(amount.amount));
    }

    public Money minus(Money amount) {
        return new Money(this.amount.subtract(amount.amount));
    }

    public Money times(double percent) {
        return new Money(this.amount.multiply(
                BigDecimal.valueOf(percent)));
    }

    public boolean isLessThan(Money other) {
        return amount.compareTo(other.amount) < 0;
    }

    public boolean isGreaterThan(Money other) {
        return amount.compareTo(other.amount) >= 0;
    }
}