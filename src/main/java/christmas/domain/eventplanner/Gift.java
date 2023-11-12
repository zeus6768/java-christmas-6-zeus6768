package christmas.domain.eventplanner;

import christmas.domain.menu.Menu;

public class Gift {

    private final Menu menu;
    private final int count;

    private Gift(Menu menu, int count){
        this.menu = menu;
        this.count = count;
    }

    public static Gift of(Menu menu, int count) {
        return new Gift(menu, count);
    }

    public Menu getMenu() {
        return menu;
    }

    public int getCount() {
        return count;
    }
}
