package christmas.controller;

import christmas.domain.eventplanner.EventPlanner;
import christmas.domain.eventplanner.Order;
import christmas.domain.eventplanner.VisitDate;
import christmas.domain.eventplanner.dto.EventResult;
import christmas.domain.eventplanner.dto.Gift;
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

        outputView.printIntro();
        VisitDate visitDate = inputView.askVisitDate();
        Order order = inputView.askOrder();
        eventPlanner.plan(visitDate, order);
        outputView.printBenefitPreviewGuide(visitDate);
        outputView.printOrders(order);

        int totalPriceBeforeDiscount = order.totalPrice();
        outputView.printPrice(totalPriceBeforeDiscount);

        Gift gift = eventPlanner.gift(order);
        outputView.printGift(gift);

        EventResult eventResult = eventPlanner.applyEvents();
        outputView.printEventResult(eventResult);
    }
}
