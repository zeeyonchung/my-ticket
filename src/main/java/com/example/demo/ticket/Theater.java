package com.example.demo.ticket;

public class Theater {

    /**
     * 영화관 입장
     * @param ticket 입장 티켓
     */
    public void enter(Ticket ticket) {
        ticket.use();
    }
}
