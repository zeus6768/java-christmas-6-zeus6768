package christmas.config;

import christmas.controller.ChristmasController;
import christmas.domain.menu.Menus;
import christmas.view.input.InputView;
import christmas.view.input.exception.InputExceptionHandler;
import christmas.view.output.OutputView;

public class ChristmasConfig {

    private static ChristmasConfig christmasConfig;

    private ChristmasController christmasController;
    private Menus menus;
    private InputView inputView;
    private InputExceptionHandler inputExceptionHandler;
    private OutputView outputView;

    public static ChristmasConfig getInstance() {
        if (christmasConfig == null) {
            christmasConfig = new ChristmasConfig();
        }
        return christmasConfig;
    }

    public ChristmasController christmasController() {
        if (christmasController == null) {
            christmasController = new ChristmasController(
                    inputView(),
                    outputView()
            );
        }
        return christmasController;
    }

    public Menus menus() {
        if (menus == null) {
            menus = new Menus();
        }
        return menus;
    }

    public InputView inputView() {
        if (inputView == null) {
            inputView = new InputView(
                    inputValidationHandler()
            );
        }
        return inputView;
    }

    public InputExceptionHandler inputValidationHandler() {
        if (inputExceptionHandler == null) {
            inputExceptionHandler = new InputExceptionHandler();
        }
        return inputExceptionHandler;
    }

    public OutputView outputView() {
        if (outputView == null) {
            outputView = new OutputView();
        }
        return outputView;
    }
}
