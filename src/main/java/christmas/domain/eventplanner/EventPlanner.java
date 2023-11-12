package christmas.domain.eventplanner;

import static christmas.domain.eventplanner.EventConstant.TOTAL_MIN_FOR_GIFT;
import static christmas.domain.menu.Drink.CHAMPAGNE;
import static christmas.domain.menu.Menu.NOT_EXISTS;

import christmas.domain.menu.Menu;
import christmas.domain.menu.Menus;

public class EventPlanner {

    private final Menus menus;

    public EventPlanner(Menus menus) {
        this.menus = menus;
    }

    public Menu gift(Order order) {
        if (order.totalPrice() >= TOTAL_MIN_FOR_GIFT) {
            return CHAMPAGNE;
        }
        return NOT_EXISTS;
    }
}
