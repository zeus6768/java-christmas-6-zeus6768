package christmas.view.output;

import static christmas.domain.eventbenefit.EventBadge.NO_BADGE;
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

import christmas.domain.eventbenefit.Event;
import christmas.domain.eventbenefit.EventBadge;
import christmas.domain.eventbenefit.EventBenefit;
import christmas.domain.eventbenefit.EventGiftBenefit;
import christmas.domain.eventplanner.EventPlanResult;
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

    public void printTotalBeforeDiscount(int price) {
        System.out.printf(TITLE_TOTAL_BEFORE_DISCOUNT);
        System.out.printf(PRICE, price);
    }

    public void printGift(EventGiftBenefit gift) {
        System.out.printf(TITLE_GIFT);
        if (gift.exists()) {
            System.out.printf(MENU, gift.getName(), GIFT_EVENT_QUANTITY);
            return;
        }
        printNotExists();
    }

    public void printEventResult(EventPlanResult result) {
        System.out.printf(TITLE_BENEFITS);
        if (result.hasBenefit()) {
            result.stream().forEach(this::printEventBenefit);
            return;
        }
        printNotExists();
    }

    public void printTotalBenefitAmount(int total) {
        System.out.printf(TITLE_BENEFIT_TOTAL);
        if (total != 0) {
            System.out.printf(BENEFIT_PRICE, total);
            return;
        }
        printNotExists();
    }

    public void printTotalAfterDiscount(int price) {
        System.out.printf(TITLE_TOTAL_AFTER_DISCOUNT);
        System.out.printf(PRICE, price);
    }

    public void printEventBadge(EventBadge badge) {
        System.out.printf(TITLE_EVENT_BADGE);
        if (badge != NO_BADGE) {
            System.out.printf(EVENT_BADGE, badge.getName());
            return;
        }
        printNotExists();
    }

    private void printOrder(Entry<Menu, Integer> menuAndCount) {
        Menu menu = menuAndCount.getKey();
        int count = menuAndCount.getValue();
        System.out.printf(MENU, menu.getName(), count);
    }

    private void printEventBenefit(Entry<Event, EventBenefit> eventBenefit) {
        Event event = eventBenefit.getKey();
        EventBenefit benefit = eventBenefit.getValue();
        if (benefit.getAmount() != 0) {
            System.out.printf(BENEFIT, event.getName(), benefit.getAmount());
        }
    }

    private void printNotExists() {
        System.out.printf(NOT_EXISTS);
    }
}
