package christmas.controller;

import christmas.domain.eventbenefit.EventBadge;
import christmas.domain.eventplanner.EventPlan;
import christmas.domain.eventplanner.Order;
import christmas.domain.eventplanner.VisitDate;
import christmas.domain.menu.Menus;
import christmas.view.input.InputView;
import christmas.view.output.OutputView;

public class ChristmasController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChristmasController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Menus.initialize();
        EventPlan eventPlan = askVisitDateAndOrder();
        printEventPlan(eventPlan);
    }

    private EventPlan askVisitDateAndOrder() {
        outputView.printIntro();
        VisitDate visitDate = inputView.askVisitDate();
        Order order = inputView.askOrder();
        return EventPlan.of(visitDate, order);
    }

    private void printEventPlan(EventPlan eventPlan) {
        outputView.printBenefitPreviewGuide(eventPlan.visitDate());
        outputView.printOrders(eventPlan.order());
        outputView.printTotalBeforeDiscount(eventPlan.totalPriceBeforeDiscount());
        outputView.printGift(eventPlan.gift());
        outputView.printEventResult(eventPlan.eventResult());
        outputView.printBenefitTotal(eventPlan.benefitTotal());
        outputView.printTotalAfterDiscount(eventPlan.totalPriceAfterDiscount());
        outputView.printEventBadge(EventBadge.from(eventPlan.eventResult()));
    }

}
