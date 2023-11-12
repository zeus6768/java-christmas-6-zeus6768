package christmas.controller;

import christmas.domain.eventplanner.EventBadge;
import christmas.domain.eventplanner.EventPlanner;
import christmas.domain.eventplanner.Order;
import christmas.domain.eventplanner.VisitDate;
import christmas.domain.eventplanner.dto.EventPlan;
import christmas.domain.menu.Menus;
import christmas.view.input.InputView;
import christmas.view.output.OutputView;

public class ChristmasController {

    private final InputView inputView;
    private final OutputView outputView;
    private final EventPlanner eventPlanner;

    public ChristmasController(InputView inputView, OutputView outputView, EventPlanner eventPlanner) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.eventPlanner = eventPlanner;
    }

    public void run() {
        Menus.initialize();
        EventPlan eventPlan = askEventPlan();
        printEventPlan(eventPlan);
    }

    private EventPlan askEventPlan() {
        outputView.printIntro();
        VisitDate visitDate = inputView.askVisitDate();
        Order order = inputView.askOrder();
        return eventPlanner.plan(visitDate, order);
    }

    private void printEventPlan(EventPlan eventPlan) {
        outputView.printBenefitPreviewGuide(eventPlan.getVisitDate());
        outputView.printOrders(eventPlan.getOrder());
        outputView.printTotalBeforeDiscount(eventPlan.getTotalPriceBeforeDiscount());
        outputView.printGift(eventPlan.getGift());
        outputView.printEventResult(eventPlan.getEventResult());
        outputView.printBenefitTotal(eventPlan.getEventResult());
        outputView.printTotalAfterDiscount(eventPlan.getTotalPriceAfterDiscount());
        outputView.printEventBadge(EventBadge.from(eventPlan.getEventResult()));
    }

}
