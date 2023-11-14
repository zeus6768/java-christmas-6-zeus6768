package christmas.domain.eventplanner;

import static christmas.domain.eventbenefit.Event.GIFT;
import static christmas.domain.eventbenefit.Event.SPECIAL;
import static christmas.domain.eventbenefit.Event.WEEKDAY;
import static christmas.domain.eventbenefit.Event.WEEKEND;
import static christmas.domain.eventbenefit.Event.X_MAS_D_DAY;

import christmas.domain.eventbenefit.EventBadge;
import christmas.domain.eventbenefit.EventGiftBenefit;

public class EventPlan {

    private final VisitDate visitDate;
    private final Order order;

    private EventPlan(VisitDate visitDate, Order order) {
        this.visitDate = visitDate;
        this.order = order;
    }

    public static EventPlan of(VisitDate visitDate, Order order) {
        return new EventPlan(visitDate, order);
    }

    public VisitDate getVisitDate() {
        return visitDate;
    }

    public Order getOrder() {
        return order;
    }

    public int getTotalPriceBeforeDiscount() {
        return order.totalPrice();
    }

    public EventGiftBenefit getGift() {
        return (EventGiftBenefit) GIFT.getBenefitOf(visitDate, order);
    }

    public EventPlanResult getEventResult() {
        return EventPlanResult.of(
                getGift(),
                X_MAS_D_DAY.getBenefitOf(visitDate, order),
                WEEKDAY.getBenefitOf(visitDate, order),
                WEEKEND.getBenefitOf(visitDate, order),
                SPECIAL.getBenefitOf(visitDate, order)
        );
    }

    public int getTotalBenefitAmount() {
        return getEventResult().getTotalBenefitAmount();
    }

    public int getTotalPriceAfterDiscount() {
        int total = getTotalPriceBeforeDiscount() - getEventResult().getTotalBenefitAmountWithoutGift();
        return Math.max(0, total);
    }

    public EventBadge getEventBadge() {
        return EventBadge.from(getEventResult().getTotalBenefitAmount());
    }
}
