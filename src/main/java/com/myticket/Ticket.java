package com.myticket;

import com.myticket.exception.TicketAlreadyUsedException;

import java.time.DateTimeException;
import java.util.Objects;

public class Ticket {
    private Screening screening;
    private Money fee;
    private int audienceCount;
    private boolean used;

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

    /**
     * 티켓 사용
     */
    public void use() {
        if (used) {
            throw new TicketAlreadyUsedException();
        }

        if (screening.isFinished()) {
            throw new DateTimeException("상영 시간이 종료되어 티켓을 사용할 수 없습니다.");
        }

        used = true;
    }
}
