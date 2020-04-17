package com.myticket;

import java.time.LocalDateTime;

public class Clock {
    private static Clock DEFAULT = new Clock();
    private static Clock instance = DEFAULT;

    public static void reset() {
        instance = DEFAULT;
    }

    public static LocalDateTime now() {
        return instance.timeNow();
    }

    protected void setInstance(Clock clock) {
        Clock.instance = clock;
    }

    public LocalDateTime timeNow() {
        return LocalDateTime.now();
    }
}
