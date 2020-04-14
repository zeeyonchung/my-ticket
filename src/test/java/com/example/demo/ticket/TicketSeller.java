package com.example.demo.ticket;

public class TicketSeller {
    public Ticket sell(TicketSellRequest sellRequest) {
        Screening screening = sellRequest.getScreening();
        Customer customer = sellRequest.getCustomer();
        int audienceCount = sellRequest.getAudienceCount();

        Ticket ticket = screening.reserve(audienceCount);
        customer.buy(ticket);

        return ticket;
    }
}
