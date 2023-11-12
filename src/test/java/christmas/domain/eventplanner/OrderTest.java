package christmas.domain.eventplanner;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.jupiter.api.Test;

import christmas.domain.menu.Menus;
import util.MyChristmasTest;

class OrderTest extends MyChristmasTest {

    @Test
    void totalPriceTest() {
        // given
        Order order = Order.from(
                Map.of(
                        Menus.find("티본스테이크"), 1,
                        Menus.find("바비큐립"), 1,
                        Menus.find("초코케이크"), 2,
                        Menus.find("제로콜라"), 1
                )
        );

        // when
        int actual = order.totalPrice();

        // then
        assertThat(actual).isEqualTo(142_000);
    }
}
