package christmas.domain.menu;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Menus {

    private static final Map<String, Menu> MENUS = new HashMap<>();

    public static void initialize() {
        putMenus(Appetizer.values());
        putMenus(Dessert.values());
        putMenus(Drink.values());
        putMenus(MainDish.values());
    }

    private static void putMenus(Menu[] menus) {
        Stream.of(menus).forEach(menu -> MENUS.put(menu.getName(), menu));
    }

    public static Menu find(String name) {
        if (MENUS.containsKey(name)){
            return MENUS.get(name);
        }
        throw new IllegalArgumentException();
    }
}
