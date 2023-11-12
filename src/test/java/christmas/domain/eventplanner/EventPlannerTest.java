package christmas.domain.eventplanner;

import static org.assertj.core.api.Assertions.assertThat;

import static christmas.domain.menu.Appetizer.TAPAS;
import static christmas.domain.menu.Dessert.CHOCOLATE_CAKE;
import static christmas.domain.menu.Dessert.ICE_CREAM;
import static christmas.domain.menu.Drink.CHAMPAGNE;
import static christmas.domain.menu.MainDish.BBQ_RIBS;
import static christmas.domain.menu.MainDish.T_BONE_STEAK;
import static christmas.domain.menu.Menu.NOT_EXISTS;

import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import christmas.domain.eventplanner.dto.EventResult;
import christmas.domain.eventplanner.dto.Gift;
import christmas.domain.menu.Menu;
import util.MyChristmasTest;

class EventPlannerTest extends MyChristmasTest {

    EventPlanner eventPlanner = new EventPlanner();

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
                Arguments.of(Map.of(TAPAS, 1), NOT_EXISTS),
                Arguments.of(Map.of(T_BONE_STEAK, 3), CHAMPAGNE)
        );
    }

    @DisplayName("방문 날짜와 주문내역에 맞는 혜택내역을 생성한다.")
    @MethodSource
    @ParameterizedTest
    void eventTest(VisitDate visitDate, Order order, int expected) {
        // given
        eventPlanner.plan(visitDate, order);

        // when
        EventResult eventResult = eventPlanner.applyEvents();

        // then
        assertThat(eventResult.sumBenefits()).isEqualTo(expected);
    }

    private static Stream<Arguments> eventTest() {
        int weekday = 4;
        int weekend = 15;
        int xMas = 25;
        int specialDay = 31;
        return Stream.of(
                // 평일 할인 6069, 크리스마스할인 1300
                Arguments.of(VisitDate.from(weekday), Order.from(Map.of(ICE_CREAM, 3)), 7369),
                // 주말 할인 4046, 크리스마스할인 2400
                Arguments.of(VisitDate.from(weekend), Order.from(Map.of(BBQ_RIBS, 2)), 6446),
                // 특별할인 1000, 크리스마스할인 3400
                Arguments.of(VisitDate.from(xMas), Order.from(Map.of(TAPAS, 5)), 4400),
                // 특별할인 1000, 평일할인 16184, 증정품 25000
                Arguments.of(VisitDate.from(specialDay), Order.from(Map.of(CHOCOLATE_CAKE, 8)), 42184)
        );
    }
}
