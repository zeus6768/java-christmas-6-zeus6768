package christmas.domain.menu;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Menus {

    private final Map<String, Menu> menus = new HashMap<>();

    public Menus() {
        putMenus(Appetizer.values());
        putMenus(Dessert.values());
        putMenus(Drink.values());
        putMenus(MainDish.values());
    }

    public Menu find(String name) {
        if (menus.containsKey(name)) {
            return menus.get(name);
        }
        throw new IllegalArgumentException();
    }

    private void putMenus(Menu[] menus) {
        Stream.of(menus).forEach(this::put);
    }

    private void put(Menu menu) {
        menus.put(menu.getName(), menu);
    }
}
