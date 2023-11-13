package christmas.domain.eventplanner;

import static christmas.domain.eventplanner.Event.CHRISTMAS_D_DAY;
import static christmas.domain.eventplanner.Event.GIFT;
import static christmas.domain.eventplanner.Event.SPECIAL;
import static christmas.domain.eventplanner.Event.WEEKDAY;
import static christmas.domain.eventplanner.Event.WEEKEND;
import static christmas.domain.eventplanner.EventConstant.GIFT_EVENT_TOTAL_MIN;
import static christmas.domain.menu.Drink.CHAMPAGNE;
import static christmas.domain.menu.Menu.NOT_EXISTS;

import christmas.domain.eventplanner.dto.EventResult;
import christmas.domain.menu.Menu;

public class EventPlanner {

    private VisitDate visitDate;
    private Order order;

    public void plan(VisitDate visitDate, Order order) {
        this.visitDate = visitDate;
        this.order = order;
    }

    public VisitDate visitDate() {
        return visitDate;
    }

    public Order order() {
        return order;
    }

    public int totalPriceBeforeDiscount() {
        return order.totalPrice();
    }

    public Menu gift() {
        if (order.totalPrice() >= GIFT_EVENT_TOTAL_MIN) {
            return CHAMPAGNE;
        }
        return NOT_EXISTS;
    }

    public EventResult eventResult() {
        return EventResult.of(
                GIFT.getBenefitOf(visitDate, order),
                CHRISTMAS_D_DAY.getBenefitOf(visitDate, order),
                WEEKDAY.getBenefitOf(visitDate, order),
                WEEKEND.getBenefitOf(visitDate, order),
                SPECIAL.getBenefitOf(visitDate, order)
        );
    }

    public int benefitTotal() {
        return eventResult().totalBenefit();
    }

    public int totalPriceAfterDiscount() {
        return order.totalPrice() - eventResult().totalBenefitWithoutGift();
    }
}
