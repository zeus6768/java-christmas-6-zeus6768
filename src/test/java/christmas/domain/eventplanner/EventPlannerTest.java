package christmas.domain.eventplanner;

import static org.assertj.core.api.Assertions.assertThat;

import static christmas.domain.menu.Appetizer.TAPAS;
import static christmas.domain.menu.Drink.CHAMPAGNE;
import static christmas.domain.menu.MainDish.T_BONE_STEAK;
import static christmas.domain.menu.Menu.NOT_EXISTS;

import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import christmas.domain.menu.Menu;
import christmas.domain.menu.Menus;
import util.MyChristmasTest;

class EventPlannerTest extends MyChristmasTest {

    EventPlanner eventPlanner = new EventPlanner(new Menus());

    @DisplayName("총주문금액에 맞는 증정 메뉴를 생성한다.")
    @MethodSource
    @ParameterizedTest
    void giftTest(Map<Menu, Integer> menuAndCounts, Menu expected) {
        // given
        Order order = Order.from(menuAndCounts);

        // when
        Gift gift = eventPlanner.gift(order);

        // then
        assertThat(gift.getMenu()).isEqualTo(expected);
    }

    private static Stream<Arguments> giftTest() {
        return Stream.of(
                Arguments.of(
                        Map.of(TAPAS, 1), NOT_EXISTS,
                        Map.of(T_BONE_STEAK, 3), CHAMPAGNE
                )
        );
    }
}
