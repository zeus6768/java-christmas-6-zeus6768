package christmas.view.output;

import static christmas.view.output.OutputMessage.GUIDE_BENEFIT_PREVIEW;
import static christmas.view.output.OutputMessage.GUIDE_INTRO;
import static christmas.view.output.OutputMessage.NOT_EXISTS;
import static christmas.view.output.OutputMessage.MENU;
import static christmas.view.output.OutputMessage.PRICE;
import static christmas.view.output.OutputMessage.TITLE_GIFT;
import static christmas.view.output.OutputMessage.TITLE_ORDER;
import static christmas.view.output.OutputMessage.TITLE_TOTAL_BEFORE_DISCOUNT;

import java.util.Map.Entry;

import christmas.domain.eventplanner.Gift;
import christmas.domain.eventplanner.Order;
import christmas.domain.eventplanner.VisitDate;
import christmas.domain.menu.Menu;

public class OutputView {

    public void printIntro() {
        System.out.printf(GUIDE_INTRO);
    }

    public void printBenefitPreviewGuide(VisitDate date) {
        System.out.printf(GUIDE_BENEFIT_PREVIEW, date.getDay());
    }

    public void printOrders(Order order) {
        System.out.printf(TITLE_ORDER);
        order.stream().forEach(this::printOrder);
    }

    public void printPrice(int price) {
        System.out.printf(TITLE_TOTAL_BEFORE_DISCOUNT);
        System.out.printf(PRICE, price);
    }

    public void printGift(Gift gift) {
        System.out.printf(TITLE_GIFT);
        if (gift.getMenu() == Menu.NOT_EXISTS) {
            printNotExists();
            return;
        }
        System.out.printf(MENU, gift.getMenu().getKoreanName(), gift.getCount());
    }

    private void printOrder(Entry<Menu, Integer> menuAndCount) {
        Menu menu = menuAndCount.getKey();
        int count = menuAndCount.getValue();
        System.out.printf(MENU, menu.getKoreanName(), count);
    }

    private void printNotExists() {
        System.out.printf(NOT_EXISTS);
    }
}
