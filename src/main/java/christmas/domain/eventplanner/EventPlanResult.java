package christmas.domain.eventplanner;

import static christmas.domain.eventbenefit.Event.GIFT;
import static christmas.domain.eventbenefit.Event.SPECIAL;
import static christmas.domain.eventbenefit.Event.WEEKDAY;
import static christmas.domain.eventbenefit.Event.WEEKEND;
import static christmas.domain.eventbenefit.Event.X_MAS_D_DAY;

import java.util.EnumMap;
import java.util.Map.Entry;
import java.util.stream.Stream;

import christmas.domain.eventbenefit.Event;
import christmas.domain.eventbenefit.EventBenefit;

public class EventPlanResult {

    private final EnumMap<Event, EventBenefit> eventBenefits = new EnumMap<>(Event.class);

    private EventPlanResult(
            EventBenefit gift,
            EventBenefit christmas,
            EventBenefit weekday,
            EventBenefit weekend,
            EventBenefit special
    ) {
        eventBenefits.put(GIFT, gift);
        eventBenefits.put(X_MAS_D_DAY, christmas);
        eventBenefits.put(WEEKDAY, weekday);
        eventBenefits.put(WEEKEND, weekend);
        eventBenefits.put(SPECIAL, special);
    }

    public static EventPlanResult of(
            EventBenefit gift,
            EventBenefit christmas,
            EventBenefit weekday,
            EventBenefit weekend,
            EventBenefit special
    ) {
        return new EventPlanResult(gift, christmas, weekday, weekend, special);
    }

    public boolean hasBenefit() {
        return getTotalBenefitAmount() != 0;
    }

    public int getTotalBenefitAmount() {
        return eventBenefits.values().stream()
                .mapToInt(EventBenefit::getAmount)
                .sum();
    }

    public int getTotalBenefitAmountWithoutGift() {
        return getTotalBenefitAmount() - getBenefitFrom(GIFT).getAmount();
    }

    public EventBenefit getBenefitFrom(Event event) {
        return eventBenefits.get(event);
    }

    public Stream<Entry<Event, EventBenefit>> stream() {
        return eventBenefits.entrySet().stream();
    }
}
