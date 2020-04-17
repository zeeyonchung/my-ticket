package com.myticket;

import com.myticket.Clock;

import java.time.LocalDateTime;

public class TestClock extends Clock {
    private LocalDateTime now;

    public TestClock() {
        setInstance(this);
    }

    public void setNow(LocalDateTime now) {
        this.now = now;
    }

    @Override
    public LocalDateTime timeNow() {
        return now != null ? now : super.timeNow();
    }
}
