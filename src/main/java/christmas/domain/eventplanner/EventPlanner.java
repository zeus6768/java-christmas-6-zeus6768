package christmas.domain.eventplanner;

import static christmas.domain.eventbenefit.Event.GIFT;
import static christmas.domain.eventbenefit.Event.SPECIAL;
import static christmas.domain.eventbenefit.Event.WEEKDAY;
import static christmas.domain.eventbenefit.Event.WEEKEND;
import static christmas.domain.eventbenefit.Event.X_MAS_D_DAY;

import christmas.domain.eventbenefit.EventGiftBenefit;

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

    public EventGiftBenefit gift() {
        return (EventGiftBenefit) GIFT.getBenefitOf(visitDate, order);
    }

    public EventPlanResult eventResult() {
        return EventPlanResult.of(
                GIFT.getBenefitOf(visitDate, order),
                X_MAS_D_DAY.getBenefitOf(visitDate, order),
                WEEKDAY.getBenefitOf(visitDate, order),
                WEEKEND.getBenefitOf(visitDate, order),
                SPECIAL.getBenefitOf(visitDate, order)
        );
    }

    public int benefitTotal() {
        return eventResult().totalBenefitAmount();
    }

    public int totalPriceAfterDiscount() {
        return order.totalPrice() - eventResult().totalBenefitAmountWithoutGift();
    }
}
