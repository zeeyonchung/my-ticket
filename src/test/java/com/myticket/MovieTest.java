package com.myticket;

import com.myticket.Money;
import com.myticket.Movie;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class MovieTest {

    @Nested
    @DisplayName("객체 생성")
    class NewMovieTest {

        @Test
        @DisplayName("title이 null이면 NullPointerException")
        void createdWithNullTitile_Then_ThrowNullPointerException() {
            assertThrows(NullPointerException.class,
                    () -> new Movie(null, Duration.ofMinutes(50), Money.ZERO));
        }

        @Test
        @DisplayName("title이 empty면 NullPointerException")
        void createdWithEmptyTitile_Then_ThrowNullPointerException() {
            assertThrows(NullPointerException.class,
                    () -> new Movie("", Duration.ofMinutes(60), Money.ZERO));
        }

        @Test
        @DisplayName("runningTime이 null이면 NullPointerException")
        void createdWithNullRunningTime_Then_ThrowNullPointerException() {
            assertThrows(NullPointerException.class,
                    () -> new Movie("title", null, Money.ZERO));
        }

        @Test
        @DisplayName("runningTime이 0분이면 IllegalArgumentException")
        void createdWith0Duration_Then_IllegalArgumentException() {
            assertThrows(IllegalArgumentException.class,
                    () -> new Movie("title", Duration.ofMinutes(0), Money.ZERO));
        }

        @Test
        @DisplayName("fee가 null이면 NullPointerException")
        void createdWithNullFee_Then_ThrowNullPointerException() {
            assertThrows(NullPointerException.class,
                    () -> new Movie("title", Duration.ofMinutes(60), null));
        }

        @Test
        @DisplayName("생성된 객체의 fee는 생성자 파라미터 값과 같다")
        void justCreated_Then_FeeIsNotChanged() {
            Movie movie = new Movie("title", Duration.ofMinutes(60), Money.wons(1234.56));

            assertEquals(Money.wons(1234.56), movie.getFee());
        }
    }
}