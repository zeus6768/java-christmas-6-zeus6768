package christmas.domain.event;

public interface EventBenefit {
    EventBenefit NO_BENEFIT = () -> 0;

    int getAmount();
}
