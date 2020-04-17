# 티켓 서비스

- v0.0.1  
    요구사항
    - 극장에서 일회성 공연을 상영한다.
    - 관객은 초대장을 티켓으로 교환하거나, 티켓을 사서 입장한다.
    
    유의하기  
    - 의존성이 강한 코드. Theater가 Audience와 TicketSeller에 직접 접근한다.  
    - Theater가 알아야 하는 것은 관람객이 극장에 입장하는 것뿐이다.

- v0.0.2  
    v0.0.1 개선
    - getter를 제거하고 행동을 위임하였다.
    - 구체적 구현이 아닌 인터페이스에만 의존한다.  
    - 각 객체는 자신을 스스로 책임진다.


- v0.0.3  
    요구사항
    - 고객이 영화 티켓을 구매하여 영화관에 입장한다.
    
    변경사항
    - 영화 구현 Movie, 상영 구현 Screening, 예매 구현 Ticket
    - 금액 구현 BigDecimal 타입에서 Money 클래스로 변경
    - 테스트 코드
        
    유의하기
    - 객체의 속성엔 직접 접근할 수 없도록 막고 적절한 public 메서드를 통해서만 내부 상태를 변경할 수 있게 해야한다.
    