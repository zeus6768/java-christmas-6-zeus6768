package christmas.domain.eventbenefit;

public class EventDiscountBenefit implements EventBenefit {

    private final int amount;

    private EventDiscountBenefit(int amount) {
        this.amount = amount;
    }

    public static EventDiscountBenefit from(int price) {
        return new EventDiscountBenefit(price);
    }

    @Override
    public int getAmount() {
        return amount;
    }
}
