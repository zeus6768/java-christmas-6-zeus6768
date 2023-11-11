package christmas.domain.menu;

public enum Appetizer implements Menu {

    WHITE_MUSHROOM_SOUP("양송이스프", 6_000),
    TAPAS("타파스", 5_500),
    CAESAR_SALAD("시저샐러드", 8_000);

    private final String koreanName;
    private final int price;

    Appetizer(String koreanName, int price) {
        this.koreanName = koreanName;
        this.price = price;
    }

    @Override
    public String getKoreanName() {
        return koreanName;
    }

    @Override
    public int getPrice() {
        return this.price;
    }
}
