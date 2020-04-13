package com.example.demo.ticket;

import org.springframework.util.StringUtils;

import java.time.Duration;
import java.util.Objects;

public class Movie {
    private String title;
    private Duration runningTime;
    private Money fee;

    public Movie(String title, Duration runningTime, Money fee) {
        Objects.requireNonNull(runningTime, "runningTime must not be null");
        Objects.requireNonNull(fee, "fee must not be null");

        if (runningTime.isZero()) {
            throw new IllegalArgumentException("runningTime must be more than zero");
        }

        if (StringUtils.isEmpty(title)) {
            throw new NullPointerException("title must not be null or empty string");
        }

        this.title = title;
        this.runningTime = runningTime;
        this.fee = fee;
    }

    public Money getFee() {
        return fee;
    }

    public Duration getRunningTime() {
        return runningTime;
    }
}
