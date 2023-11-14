package christmas.controller;

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
        EventPlan eventPlan = planEventByVisitDateAndOrder();
        printEventPlan(eventPlan);
    }

    private EventPlan planEventByVisitDateAndOrder() {
        outputView.printIntro();
        VisitDate visitDate = inputView.askVisitDate();
        Order order = inputView.askOrder();
        return EventPlan.of(visitDate, order);
    }

    private void printEventPlan(EventPlan eventPlan) {
        outputView.printBenefitPreviewGuide(eventPlan.getVisitDate());
        outputView.printOrders(eventPlan.getOrder());
        outputView.printTotalBeforeDiscount(eventPlan.getTotalPriceBeforeDiscount());
        outputView.printGift(eventPlan.getGift());
        outputView.printEventResult(eventPlan.getEventResult());
        outputView.printTotalBenefitAmount(eventPlan.getTotalBenefitAmount());
        outputView.printTotalAfterDiscount(eventPlan.getTotalPriceAfterDiscount());
        outputView.printEventBadge(eventPlan.getEventBadge());
    }
}
