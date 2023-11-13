package christmas.domain.eventbenefit;

public interface EventBenefit {
    EventBenefit NO_BENEFIT = () -> 0;

    int getAmount();
}
