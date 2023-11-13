package christmas.controller;

import christmas.domain.eventplanner.EventBadge;
import christmas.domain.eventplanner.EventPlanner;
import christmas.domain.eventplanner.Order;
import christmas.domain.eventplanner.VisitDate;
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
        askEventPlan();
        printEventPlan();
    }

    private void askEventPlan() {
        outputView.printIntro();
        VisitDate visitDate = inputView.askVisitDate();
        Order order = inputView.askOrder();
        eventPlanner.plan(visitDate, order);
    }

    private void printEventPlan() {
        outputView.printBenefitPreviewGuide(eventPlanner.visitDate());
        outputView.printOrders(eventPlanner.order());
        outputView.printTotalBeforeDiscount(eventPlanner.totalPriceBeforeDiscount());
        outputView.printGift(eventPlanner.gift());
        outputView.printEventResult(eventPlanner.eventResult());
        outputView.printBenefitTotal(eventPlanner.benefitTotal());
        outputView.printTotalAfterDiscount(eventPlanner.totalPriceAfterDiscount());
        outputView.printEventBadge(EventBadge.from(eventPlanner.eventResult()));
    }

}
