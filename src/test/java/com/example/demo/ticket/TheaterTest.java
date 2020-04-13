package com.example.demo.ticket;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.only;

@ExtendWith(MockitoExtension.class)
public class TheaterTest {

    @Mock
    private Ticket ticketMock;

    @Test
    @DisplayName("입장시 티켓을 한 번 사용처리 한다")
    void enter_Then_useTicket() {
        Theater theater = new Theater();
        theater.enter(ticketMock);

        then(ticketMock).should(only()).use();
    }
}
