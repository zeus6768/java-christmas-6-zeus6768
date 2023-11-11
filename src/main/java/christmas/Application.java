package christmas;

import christmas.config.ChristmasConfig;
import christmas.controller.ChristmasController;

public class Application {
    public static void main(String[] args) {
        ChristmasConfig config = ChristmasConfig.getInstance();
        ChristmasController controller = config.christmasController();
        controller.run();
    }
}
