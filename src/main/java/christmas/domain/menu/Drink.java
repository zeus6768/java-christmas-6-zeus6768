package christmas.domain.menu;

public enum Drink implements Menu {

    ZERO_COLA("제로콜라", 3_000),
    RED_WINE("레드와인", 60_000),
    CHAMPAGNE("샴페인", 25_000);

    private final String koreanName;
    private final int price;

    Drink(String koreanName, int price) {
        this.koreanName = koreanName;
        this.price = price;
    }

    public static void init() {
        Menu.putMenus(values());
    }

    @Override
    public String getKoreanName() {
        return koreanName;
    }

    @Override
    public int getPrice() {
        return price;
    }
}
