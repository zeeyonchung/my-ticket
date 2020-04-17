package com.myticket;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TicketSellRequest {

    private Customer customer;
    private Screening screening;
    private int audienceCount;
}
