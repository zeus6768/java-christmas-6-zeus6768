package christmas.domain.event;

import static christmas.domain.event.EventBenefit.NO_BENEFIT;
import static christmas.domain.eventplanner.EventCalendar.isSpecialDay;
import static christmas.domain.eventplanner.EventCalendar.isWeekEnd;
import static christmas.domain.eventplanner.EventCalendar.isWeekday;
import static christmas.domain.eventplanner.EventCalendar.isXMasDDay;
import static christmas.domain.eventplanner.EventConstant.GIFT_EVENT_TOTAL_MIN;
import static christmas.domain.eventplanner.EventConstant.SPECIAL_EVENT_TOTAL_DISCOUNT;
import static christmas.domain.eventplanner.EventConstant.WEEKDAY_EVENT_DESSERT_DISCOUNT;
import static christmas.domain.eventplanner.EventConstant.WEEKEND_EVENT_MAIN_DISH_DISCOUNT;
import static christmas.domain.eventplanner.EventConstant.X_MAS_EVENT_DAILY_DISCOUNT;
import static christmas.domain.eventplanner.EventConstant.X_MAS_EVENT_DEFAULT_DISCOUNT;
import static christmas.domain.menu.Drink.CHAMPAGNE;
import static christmas.domain.menu.Menu.NO_MENU;

import java.util.function.BiFunction;
import java.util.function.Predicate;

import christmas.domain.eventplanner.Order;
import christmas.domain.eventplanner.VisitDate;
import christmas.domain.menu.Dessert;
import christmas.domain.menu.MainDish;
import christmas.domain.menu.Menu;

public enum Event {

    X_MAS_D_DAY("크리스마스 디데이 할인", ((visitDate, order) -> getXMasEventBenefit(visitDate))),
    WEEKDAY("평일 할인", Event::getWeekdayEventBenefit),
    WEEKEND("주말 할인", Event::getWeekendEventBenefit),
    SPECIAL("스페셜 할인", Event::getSpecialEventBenefit),
    GIFT("증정 이벤트", ((visitDate, order) -> getGiftEventBenefit(order)));

    private final String name;
    private final BiFunction<VisitDate, Order, EventBenefit> benefit;

    Event(String name, BiFunction<VisitDate, Order, EventBenefit> benefit) {
        this.name = name;
        this.benefit = benefit;
    }

    public String getName() {
        return name;
    }

    public EventBenefit getBenefitOf(VisitDate visitDate, Order order) {
        return benefit.apply(visitDate, order);
    }

    private static EventBenefit getXMasEventBenefit(VisitDate visitDate) {
        if (isXMasDDay(visitDate)) {
            return EventDiscountBenefit.from(
                    visitDate.getDay() * X_MAS_EVENT_DAILY_DISCOUNT + X_MAS_EVENT_DEFAULT_DISCOUNT
            );
        }
        return NO_BENEFIT;
    }

    private static EventBenefit getWeekdayEventBenefit(VisitDate visitDate, Order order) {
        if (isWeekday(visitDate)) {
            int totalPrice = getBenefitPriceFromOrder(
                    order,
                    menu -> menu instanceof Dessert,
                    WEEKDAY_EVENT_DESSERT_DISCOUNT);
            return EventDiscountBenefit.from(totalPrice);
        }
        return NO_BENEFIT;
    }

    private static EventBenefit getWeekendEventBenefit(VisitDate visitDate, Order order) {
        if (isWeekEnd(visitDate)) {
            int totalPrice = getBenefitPriceFromOrder(
                    order,
                    menu -> menu instanceof MainDish,
                    WEEKEND_EVENT_MAIN_DISH_DISCOUNT);
            return EventDiscountBenefit.from(totalPrice);
        }
        return NO_BENEFIT;
    }

    private static EventBenefit getSpecialEventBenefit(VisitDate visitDate, Order order) {
        if (isSpecialDay(visitDate)) {
            return EventDiscountBenefit.from(SPECIAL_EVENT_TOTAL_DISCOUNT);
        }
        return NO_BENEFIT;
    }

    private static EventBenefit getGiftEventBenefit(Order order) {
        if (order.totalPrice() >= GIFT_EVENT_TOTAL_MIN) {
            return EventGiftBenefit.from(CHAMPAGNE);
        }
        return EventGiftBenefit.from(NO_MENU);
    }

    private static int getBenefitPriceFromOrder(Order order, Predicate<Menu> condition, int discount) {
        return order.stream()
                .filter(menuAndCount -> condition.test(menuAndCount.getKey()))
                .mapToInt(menuAndCount -> menuAndCount.getValue() * discount)
                .sum();
    }
}
