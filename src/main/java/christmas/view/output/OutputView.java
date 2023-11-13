package christmas.view.output;

import static christmas.domain.eventplanner.EventConstant.GIFT_EVENT_QUANTITY;
import static christmas.view.output.OutputMessage.BENEFIT;
import static christmas.view.output.OutputMessage.BENEFIT_PRICE;
import static christmas.view.output.OutputMessage.EVENT_BADGE;
import static christmas.view.output.OutputMessage.GUIDE_BENEFIT_PREVIEW;
import static christmas.view.output.OutputMessage.GUIDE_INTRO;
import static christmas.view.output.OutputMessage.MENU;
import static christmas.view.output.OutputMessage.NOT_EXISTS;
import static christmas.view.output.OutputMessage.PRICE;
import static christmas.view.output.OutputMessage.TITLE_BENEFITS;
import static christmas.view.output.OutputMessage.TITLE_BENEFIT_TOTAL;
import static christmas.view.output.OutputMessage.TITLE_EVENT_BADGE;
import static christmas.view.output.OutputMessage.TITLE_GIFT;
import static christmas.view.output.OutputMessage.TITLE_ORDER;
import static christmas.view.output.OutputMessage.TITLE_TOTAL_AFTER_DISCOUNT;
import static christmas.view.output.OutputMessage.TITLE_TOTAL_BEFORE_DISCOUNT;

import java.util.Map.Entry;

import christmas.domain.eventplanner.Event;
import christmas.domain.eventplanner.EventBadge;
import christmas.domain.eventplanner.Order;
import christmas.domain.eventplanner.VisitDate;
import christmas.domain.eventplanner.dto.EventResult;
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

    public void printTotalBeforeDiscount(int price) {
        System.out.printf(TITLE_TOTAL_BEFORE_DISCOUNT);
        printPrice(price);
    }

    public void printGift(Menu gift) {
        System.out.printf(TITLE_GIFT);
        if (gift != Menu.NOT_EXISTS) {
            System.out.printf(MENU, gift.getName(), GIFT_EVENT_QUANTITY);
            return;
        }
        printNotExists();
    }

    public void printEventResult(EventResult result) {
        System.out.printf(TITLE_BENEFITS);
        if (result.hasBenefit()) {
            result.stream().forEach(this::printEventBenefit);
            return;
        }
        printNotExists();
    }

    public void printBenefitTotal(int total) {
        System.out.printf(TITLE_BENEFIT_TOTAL);
        if (total != 0) {
            System.out.printf(BENEFIT_PRICE, total);
            return;
        }
        printNotExists();
    }

    public void printTotalAfterDiscount(int price) {
        System.out.printf(TITLE_TOTAL_AFTER_DISCOUNT);
        printPrice(price);
    }

    public void printEventBadge(EventBadge badge) {
        System.out.printf(TITLE_EVENT_BADGE);
        System.out.printf(EVENT_BADGE, badge.getName());
    }

    private void printOrder(Entry<Menu, Integer> menuAndCount) {
        Menu menu = menuAndCount.getKey();
        int count = menuAndCount.getValue();
        System.out.printf(MENU, menu.getName(), count);
    }

    private void printPrice(int price) {
        System.out.printf(PRICE, price);
    }

    private void printEventBenefit(Entry<Event, Integer> eventBenefit) {
        Event event = eventBenefit.getKey();
        int benefit = eventBenefit.getValue();
        if (benefit != 0) {
            System.out.printf(BENEFIT, event.getName(), benefit);
        }
    }

    private void printNotExists() {
        System.out.printf(NOT_EXISTS);
    }
}
