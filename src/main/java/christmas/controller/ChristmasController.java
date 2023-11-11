package christmas.controller;

import christmas.domain.VisitDate;
import christmas.domain.eventplanner.Order;
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
        outputView.printIntro();
        VisitDate visitDate = inputView.askVisitDate();
        Order order = inputView.askOrder();
        outputView.printBenefitPreviewGuide(visitDate);
        outputView.printOrders(order);
    }
}
