package com.example.demo.ticket;

import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode
public class Money {
    public static final Money ZERO = Money.wons(BigDecimal.ZERO);

    private final BigDecimal amount;

    Money(BigDecimal amount) {
        this.amount = amount;
    }

    public static Money wons(double amount) {
        return new Money(BigDecimal.valueOf(amount));
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

    public Money times(int times) {
        return new Money(this.amount.multiply(
                BigDecimal.valueOf(times)));
    }

    public boolean isLessThan(Money other) {
        return amount.compareTo(other.amount) < 0;
    }

    public boolean isGreaterThan(Money other) {
        return amount.compareTo(other.amount) > 0;
    }
}