package christmas.domain.menu;

public enum MainDish implements Menu {

    T_BONE_STEAK("티본스테이크", 55_000),
    BBQ_RIBS("바비큐립", 54_000),
    SEAFOOD_PASTA("해산물파스타", 35_000),
    CHRISTMAS_PASTA("크리스마스파스타", 25_000);

    private final String koreanName;
    private final int price;

    MainDish(String koreanName, int price) {
        this.koreanName = koreanName;
        this.price = price;
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