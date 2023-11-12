package christmas.domain.eventplanner;

import static christmas.domain.eventplanner.EventCalendar.isChristmasDDay;
import static christmas.domain.eventplanner.EventCalendar.isSpecialDay;
import static christmas.domain.eventplanner.EventCalendar.isWeekEnd;
import static christmas.domain.eventplanner.EventCalendar.isWeekday;
import static christmas.domain.eventplanner.EventConstant.CHRISTMAS_EVENT_DAILY_DISCOUNT;
import static christmas.domain.eventplanner.EventConstant.CHRISTMAS_EVENT_DEFAULT_DISCOUNT;
import static christmas.domain.eventplanner.EventConstant.GIFT_EVENT_QUANTITY;
import static christmas.domain.eventplanner.EventConstant.GIFT_EVENT_TOTAL_MIN;
import static christmas.domain.eventplanner.EventConstant.SPECIAL_EVENT_TOTAL_DISCOUNT;
import static christmas.domain.eventplanner.EventConstant.WEEKDAY_EVENT_DESSERT_DISCOUNT;
import static christmas.domain.eventplanner.EventConstant.WEEKEND_EVENT_MAIN_DISH_DISCOUNT;
import static christmas.domain.menu.Drink.CHAMPAGNE;
import static christmas.domain.menu.Menu.NOT_EXISTS;

import java.util.function.Predicate;

import christmas.domain.eventplanner.dto.EventResult;
import christmas.domain.eventplanner.dto.Gift;
import christmas.domain.menu.Dessert;
import christmas.domain.menu.MainDish;
import christmas.domain.menu.Menu;

public class EventPlanner {

    private VisitDate visitDate;
    private Order order;

    public void plan(VisitDate visitDate, Order order) {
        this.visitDate = visitDate;
        this.order = order;
    }

    public Gift gift(Order order) {
        if (order.totalPrice() >= GIFT_EVENT_TOTAL_MIN) {
            return Gift.of(CHAMPAGNE, GIFT_EVENT_QUANTITY);
        }
        return Gift.of(NOT_EXISTS, 0);
    }

    public EventResult applyEvents() {
        return EventResult.of(
                getGiftBenefit(),
                getChristmasEventBenefit(),
                getWeekdayEventBenefit(),
                getWeekendEventBenefit(),
                getSpecialEventBenefit()
        );
    }

    private int getGiftBenefit() {
        return gift(order).getTotal();
    }

    private int getChristmasEventBenefit() {
        if (isChristmasDDay(visitDate)) {
            return visitDate.getDay() * CHRISTMAS_EVENT_DAILY_DISCOUNT + CHRISTMAS_EVENT_DEFAULT_DISCOUNT;
        }
        return 0;
    }

    private int getWeekdayEventBenefit() {
        if (isWeekday(visitDate)) {
            return computeEventBenefitFromOrder(menu -> menu instanceof Dessert, WEEKDAY_EVENT_DESSERT_DISCOUNT);
        }
        return 0;
    }

    private int getWeekendEventBenefit() {
        if (isWeekEnd(visitDate)) {
            return computeEventBenefitFromOrder(menu -> menu instanceof MainDish, WEEKEND_EVENT_MAIN_DISH_DISCOUNT);
        }
        return 0;
    }

    private int getSpecialEventBenefit() {
        if (isSpecialDay(visitDate)) {
            return SPECIAL_EVENT_TOTAL_DISCOUNT;
        }
        return 0;
    }

    private int computeEventBenefitFromOrder(Predicate<Menu> condition, int discount) {
        return order.stream()
                .filter(menuAndCount -> condition.test(menuAndCount.getKey()))
                .mapToInt(menuAndCount -> menuAndCount.getValue() * discount)
                .sum();
    }
}
