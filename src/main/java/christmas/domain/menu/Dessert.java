package christmas.domain.menu;

public enum Dessert implements Menu {

    CHOCOLATE_CAKE("초코케이크", 15_000),
    ICE_CREAM("아이스크림", 5_000);

    private final String koreanName;
    private final int price;

    Dessert(String koreanName, int price) {
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
