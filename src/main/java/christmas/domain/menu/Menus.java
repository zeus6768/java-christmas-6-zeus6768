package christmas.domain.menu;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Menus {

    private static final Map<String, Menu> MENUS = new LinkedHashMap<>();

    public static void initialize() {
        putMenus(Appetizer.values());
        putMenus(Dessert.values());
        putMenus(Drink.values());
        putMenus(MainDish.values());
    }

    private static void putMenus(Menu[] menus) {
        Stream.of(menus).forEach(menu -> MENUS.put(menu.getKoreanName(), menu));
    }

    public static Menu find(String koreanName) {
        if (MENUS.containsKey(koreanName)){
            return MENUS.get(koreanName);
        }
        throw new IllegalArgumentException();
    }
}
