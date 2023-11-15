package christmas.domain.eventbenefit;

import christmas.domain.menu.Menu;

public class EventGiftBenefit implements EventBenefit, Menu {

    private final Menu menu;

    private EventGiftBenefit(Menu menu) {
        this.menu = menu;
    }

    public static EventGiftBenefit from(Menu menu) {
        return new EventGiftBenefit(menu);
    }

    public boolean exists() {
        return menu != Menu.NO_MENU;
    }

    @Override
    public String getName() {
        return menu.getName();
    }

    @Override
    public int getPrice() {
        return menu.getPrice();
    }

    @Override
    public int getAmount() {
        return getPrice();
    }
}
