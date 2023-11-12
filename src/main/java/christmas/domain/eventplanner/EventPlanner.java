package christmas.domain.eventplanner;

import static christmas.domain.eventplanner.EventConstant.GIFT_EVENT_QUANTITY;
import static christmas.domain.eventplanner.EventConstant.GIFT_EVENT_TOTAL_MIN;
import static christmas.domain.menu.Drink.CHAMPAGNE;
import static christmas.domain.menu.Menu.NOT_EXISTS;

import christmas.domain.menu.Menus;

public class EventPlanner {

    private final Menus menus;

    public EventPlanner(Menus menus) {
        this.menus = menus;
    }

    public Gift gift(Order order) {
        if (order.totalPrice() >= GIFT_EVENT_TOTAL_MIN) {
            return Gift.of(CHAMPAGNE, GIFT_EVENT_QUANTITY);
        }
        return Gift.of(NOT_EXISTS, 0);
    }
}
