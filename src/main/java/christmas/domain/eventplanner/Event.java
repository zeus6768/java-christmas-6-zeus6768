package christmas.domain.eventplanner;

import static christmas.domain.eventplanner.EventCalendar.isChristmasDDay;
import static christmas.domain.eventplanner.EventCalendar.isSpecialDay;
import static christmas.domain.eventplanner.EventCalendar.isWeekEnd;
import static christmas.domain.eventplanner.EventCalendar.isWeekday;
import static christmas.domain.eventplanner.EventConstant.CHRISTMAS_EVENT_DAILY_DISCOUNT;
import static christmas.domain.eventplanner.EventConstant.CHRISTMAS_EVENT_DEFAULT_DISCOUNT;
import static christmas.domain.eventplanner.EventConstant.GIFT_EVENT_TOTAL_MIN;
import static christmas.domain.eventplanner.EventConstant.SPECIAL_EVENT_TOTAL_DISCOUNT;
import static christmas.domain.eventplanner.EventConstant.WEEKDAY_EVENT_DESSERT_DISCOUNT;
import static christmas.domain.eventplanner.EventConstant.WEEKEND_EVENT_MAIN_DISH_DISCOUNT;
import static christmas.domain.menu.Drink.CHAMPAGNE;

import java.util.function.BiFunction;
import java.util.function.Predicate;

import christmas.domain.menu.Dessert;
import christmas.domain.menu.MainDish;
import christmas.domain.menu.Menu;

public enum Event {

    CHRISTMAS_D_DAY("크리스마스 디데이 할인",
            ((visitDate, order) -> {
                if (isChristmasDDay(visitDate)) {
                    return visitDate.getDay() * CHRISTMAS_EVENT_DAILY_DISCOUNT + CHRISTMAS_EVENT_DEFAULT_DISCOUNT;
                }
                return 0;
            })),

    WEEKDAY("평일 할인",
            ((visitDate, order) -> {
                if (isWeekday(visitDate)) {
                    return benefitFromOrder(order, menu -> menu instanceof Dessert,
                            WEEKDAY_EVENT_DESSERT_DISCOUNT);
                }
                return 0;
            })),

    WEEKEND("주말 할인",
            ((visitDate, order) -> {
                if (isWeekEnd(visitDate)) {
                    return benefitFromOrder(order, menu -> menu instanceof MainDish,
                            WEEKEND_EVENT_MAIN_DISH_DISCOUNT);
                }
                return 0;
            })),

    SPECIAL("스페셜 할인",
            (visitDate, order) -> {
                if (isSpecialDay(visitDate)) {
                    return SPECIAL_EVENT_TOTAL_DISCOUNT;
                }
                return 0;
            }),

    GIFT("증정 이벤트",
            ((visitDate, order) -> {
                if (order.totalPrice() >= GIFT_EVENT_TOTAL_MIN) {
                    return CHAMPAGNE.getPrice();
                }
                return 0;
            }));

    private final String name;
    private final BiFunction<VisitDate, Order, Integer> benefit;

    Event(String name, BiFunction<VisitDate, Order, Integer> benefit) {
        this.name = name;
        this.benefit = benefit;
    }

    private static int benefitFromOrder(Order order, Predicate<Menu> condition, int discount) {
        return order.stream()
                .filter(menuAndCount -> condition.test(menuAndCount.getKey()))
                .mapToInt(menuAndCount -> menuAndCount.getValue() * discount)
                .sum();
    }

    public String getName() {
        return name;
    }

    public int getBenefitOf(VisitDate visitDate, Order order) {
        return benefit.apply(visitDate, order);
    }
}
