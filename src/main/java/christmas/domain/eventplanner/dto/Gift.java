package christmas.domain.eventplanner.dto;

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

    public boolean exists() {
        return menu != Menu.NOT_EXISTS;
    }

    public int getTotal() {
        return menu.getPrice() * count;
    }

    public String getName() {
        return menu.getName();
    }

    public int getCount() {
        return count;
    }
}
