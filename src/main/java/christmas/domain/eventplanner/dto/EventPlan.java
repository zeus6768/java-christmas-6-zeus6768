package christmas.domain.eventplanner.dto;

import christmas.domain.eventplanner.Order;
import christmas.domain.eventplanner.VisitDate;

public class EventPlan {

    private final VisitDate visitDate;
    private final Order order;
    private final int totalPriceBeforeDiscount;
    private final Gift gift;
    private final EventResult eventResult;
    private final int totalPriceAfterDiscount;

    private EventPlan(
            VisitDate visitDate,
            Order order,
            int totalPriceBeforeDiscount,
            Gift gift,
            EventResult eventResult,
            int totalPriceAfterDiscount
    ) {
        this.visitDate = visitDate;
        this.order = order;
        this.totalPriceBeforeDiscount = totalPriceBeforeDiscount;
        this.gift = gift;
        this.eventResult = eventResult;
        this.totalPriceAfterDiscount = totalPriceAfterDiscount;
    }

    public static EventPlan of(
            VisitDate visitDate,
            Order order,
            int totalPriceBeforeDiscount,
            Gift gift,
            EventResult eventResult,
            int totalPriceAfterDiscount
    ) {
        return new EventPlan(visitDate, order, totalPriceBeforeDiscount, gift, eventResult, totalPriceAfterDiscount);
    }

    public VisitDate getVisitDate() {
        return visitDate;
    }

    public Order getOrder() {
        return order;
    }

    public int getTotalPriceBeforeDiscount() {
        return totalPriceBeforeDiscount;
    }

    public Gift getGift() {
        return gift;
    }

    public EventResult getEventResult() {
        return eventResult;
    }

    public int getTotalPriceAfterDiscount() {
        return totalPriceAfterDiscount;
    }
}
