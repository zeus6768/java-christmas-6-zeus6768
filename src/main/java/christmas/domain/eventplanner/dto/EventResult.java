package christmas.domain.eventplanner.dto;

import static christmas.domain.eventplanner.Event.GIFT;
import static christmas.domain.eventplanner.Event.SPECIAL;
import static christmas.domain.eventplanner.Event.WEEKDAY;
import static christmas.domain.eventplanner.Event.WEEKEND;
import static christmas.domain.eventplanner.Event.X_MAS_D_DAY;

import java.util.EnumMap;
import java.util.Map.Entry;
import java.util.stream.Stream;

import christmas.domain.eventplanner.Event;

public class EventResult {

    private final EnumMap<Event, Integer> eventBenefit = new EnumMap<>(Event.class);

    private EventResult(
            int giftBenefit,
            int christmasBenefit,
            int weekdayBenefit,
            int weekendBenefit,
            int specialBenefit
    ) {
        eventBenefit.put(GIFT, giftBenefit);
        eventBenefit.put(X_MAS_D_DAY, christmasBenefit);
        eventBenefit.put(WEEKDAY, weekdayBenefit);
        eventBenefit.put(WEEKEND, weekendBenefit);
        eventBenefit.put(SPECIAL, specialBenefit);
    }

    public static EventResult of(
            int gift,
            int christmasBenefit,
            int weekdayBenefit,
            int weekendBenefit,
            int specialBenefit
    ) {
        return new EventResult(gift, christmasBenefit, weekdayBenefit, weekendBenefit, specialBenefit);
    }

    public boolean hasBenefit() {
        return totalBenefit() != 0;
    }

    public int totalBenefit() {
        return eventBenefit.values().stream().mapToInt(i -> i).sum();
    }

    public Stream<Entry<Event, Integer>> stream() {
        return eventBenefit.entrySet().stream();
    }
}
