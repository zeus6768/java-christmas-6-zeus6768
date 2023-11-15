package christmas.domain.eventplanner;

import static org.assertj.core.api.Assertions.assertThat;

import static christmas.domain.eventbenefit.EventBadge.NO_BADGE;
import static christmas.domain.eventbenefit.EventBadge.SANTA;
import static christmas.domain.eventbenefit.EventBadge.STAR;
import static christmas.domain.eventbenefit.EventBadge.TREE;
import static christmas.domain.menu.Drink.CHAMPAGNE;
import static christmas.domain.menu.Menu.NO_MENU;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import christmas.domain.eventbenefit.EventBadge;
import christmas.domain.menu.Menu;
import christmas.view.input.InputView;
import christmas.view.input.exception.InputExceptionHandler;
import util.MyChristmasTest;

class EventPlanTest extends MyChristmasTest {

    InputView inputView = new InputView(new InputExceptionHandler());

    @DisplayName("우테코 식당 이벤트 플래너 테스트")
    @MethodSource("testCases")
    @ParameterizedTest
    void test1(
            String visitDateInput, String orderInput,
            int totalPriceBeforeDiscount,
            Menu gift,
            int benefitAmount,
            int totalPriceAfterDiscount,
            EventBadge badge
    ) {
        // given
        setInput(visitDateInput, orderInput);
        VisitDate visitDate = inputView.askVisitDate();
        Order order = inputView.askOrder();

        // when
        EventPlan eventPlan = EventPlan.of(visitDate, order);

        // then
        assertThat(eventPlan.getTotalPriceBeforeDiscount()).isEqualTo(totalPriceBeforeDiscount);
        assertThat(eventPlan.getGift().getName()).isEqualTo(gift.getName());
        assertThat(eventPlan.getTotalBenefitAmount()).isEqualTo(benefitAmount);
        assertThat(eventPlan.getTotalPriceAfterDiscount()).isEqualTo(totalPriceAfterDiscount);
        assertThat(eventPlan.getEventBadge()).isEqualTo(badge);
    }

    private static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of("26", "아이스크림-1", 5000, NO_MENU, 2023, 2977, NO_BADGE),
                Arguments.of("26", "초코케이크-3", 45000, NO_MENU, 6069, 38931, STAR),
                Arguments.of("26", "초코케이크-5", 75000, NO_MENU, 10115, 64885, TREE),
                Arguments.of("26", "아이스크림-10", 50000, NO_MENU, 20230, 29770, SANTA),
                Arguments.of("26", "해산물파스타-3,초코케이크-1", 120000, CHAMPAGNE, 27023, 117977, SANTA),
                Arguments.of("31", "아이스크림-1", 5000, NO_MENU, 3023, 1977, NO_BADGE),
                Arguments.of("31", "초코케이크-3", 45000, NO_MENU, 7069, 37931, STAR),
                Arguments.of("31", "초코케이크-5", 75000, NO_MENU, 11115, 63885, TREE),
                Arguments.of("31", "아이스크림-10", 50000, NO_MENU, 21230, 28770, SANTA),
                Arguments.of("31", "해산물파스타-3,초코케이크-1", 120000, CHAMPAGNE, 28023, 116977, SANTA),
                Arguments.of("29", "크리스마스파스타-1", 25000, NO_MENU, 2023, 22977, NO_BADGE),
                Arguments.of("29", "크리스마스파스타-3", 75000, NO_MENU, 6069, 68931, STAR),
                Arguments.of("29", "크리스마스파스타-5", 125000, CHAMPAGNE, 35115, 114885, SANTA),
                Arguments.of("4", "아이스크림-1", 5000, NO_MENU, 3323, 1677, NO_BADGE),
                Arguments.of("4", "초코케이크-3", 45000, NO_MENU, 7369, 37631, STAR),
                Arguments.of("4", "초코케이크-5", 75000, NO_MENU, 11415, 63585, TREE),
                Arguments.of("4", "아이스크림-10", 50000, NO_MENU, 21530, 28470, SANTA),
                Arguments.of("4", "해산물파스타-3,초코케이크-1", 120000, CHAMPAGNE, 28323, 116677, SANTA),
                Arguments.of("25", "아이스크림-1", 5000, NO_MENU, 6423, 0, STAR),
                Arguments.of("25", "초코케이크-2", 30000, NO_MENU, 8446, 21554, STAR),
                Arguments.of("25", "초코케이크-3", 45000, NO_MENU, 10469, 34531, TREE),
                Arguments.of("25", "아이스크림-10", 50000, NO_MENU, 24630, 25370, SANTA),
                Arguments.of("25", "해산물파스타-3,초코케이크-1", 120000, CHAMPAGNE, 31423, 113577, SANTA),
                Arguments.of("8", "크리스마스파스타-1", 25000, NO_MENU, 3723, 21277, NO_BADGE),
                Arguments.of("8", "크리스마스파스타-3", 75000, NO_MENU, 7769, 67231, STAR),
                Arguments.of("22", "크리스마스파스타-4", 100000, NO_MENU, 11192, 88808, TREE),
                Arguments.of("22", "크리스마스파스타-5", 125000, CHAMPAGNE, 38215, 111785, SANTA)
        );
    }
}
