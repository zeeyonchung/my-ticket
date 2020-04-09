package com.example.demo.ticket;

public class Theater {
    private TicketSeller ticketSeller;

    public Theater(TicketSeller ticketSeller) {
        this.ticketSeller = ticketSeller;
    }

    public void enter(Audience audience) {
        Ticket ticket = ticketSeller.getTicketOffice().getTicket();
        Bag bag = audience.getBag();

        if (!bag.hasInvitation()) {
            bag.minusAmount(ticket.getFee());
            ticketSeller.getTicketOffice().plusAmount(ticket.getFee());
        }

        bag.setTicket(ticket);
    }
}
