package christmas.domain.menu;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public interface Menu {

    Map<String, Menu> MENUS = new HashMap<>();

    static void init() {
        Appetizer.init();
        Dessert.init();
        Drink.init();
        MainDish.init();
    }

    static <M extends Enum<M> & Menu> void putMenus(M[] menus) {
        Stream.of(menus).forEach(menu -> MENUS.put(menu.getKoreanName(), menu));
    }

    static Menu find(String koreanName) {
        if (MENUS.containsKey(koreanName)){
            return MENUS.get(koreanName);
        }
        throw new IllegalArgumentException();
    }

    String getKoreanName();
    int getPrice();
}
