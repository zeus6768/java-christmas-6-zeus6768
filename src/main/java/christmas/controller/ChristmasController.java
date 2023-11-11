package christmas.controller;

import christmas.domain.VisitDate;
import christmas.domain.eventplanner.EventPlanner;
import christmas.domain.eventplanner.Order;
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
        outputView.printIntro();
        VisitDate visitDate = inputView.askVisitDate();
        Order order = inputView.askOrder();
        outputView.printBenefitPreviewGuide(visitDate);
        outputView.printOrders(order);
        int totalPriceBeforeDiscount = order.totalPrice();
        outputView.printPrice(totalPriceBeforeDiscount);
    }
}
