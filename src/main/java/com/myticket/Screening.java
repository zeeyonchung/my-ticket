package com.myticket;

import java.time.DateTimeException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

public class Screening {
    private Movie movie;
    private LocalDateTime screenedAt;

    public Screening(Movie movie, LocalDateTime screenedAt) {
        Objects.requireNonNull(movie, "movie must not be null");
        Objects.requireNonNull(screenedAt, "screenedAt must not be null");

        if (Clock.now().isAfter(screenedAt)) {
            throw new DateTimeException("상영 시간이 과거이므로 상영을 생성할 수 없습니다.");
        }

        this.movie = movie;
        this.screenedAt = screenedAt;
    }

    /**
     * 상영 예매
     * @param audienceCount 인원 수
     * @return 상영 예매 인스턴스
     */
    public Ticket reserve(int audienceCount) {
        if (Clock.now().isAfter(screenedAt)) {
            throw new DateTimeException("상영이 시작되어 예매할 수 없습니다.");
        }

        return new Ticket(this, calculateFee(audienceCount), audienceCount);
    }

    private Money calculateFee(int audienceCount) {
        return movie.getFee().times(audienceCount);
    }

    public boolean isFinished() {
        Duration runningTime = movie.getRunningTime();
        LocalDateTime endTime = screenedAt.plus(runningTime);
        return Clock.now().isAfter(endTime);
    }
}
