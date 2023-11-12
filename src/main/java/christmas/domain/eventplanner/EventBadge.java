package christmas.domain.eventplanner;

import java.util.Arrays;
import java.util.function.Function;

import christmas.domain.eventplanner.dto.EventResult;

public enum EventBadge {
    SANTA("산타", (totalBenefit) -> totalBenefit >= 20_000),
    TREE("트리", (totalBenefit) -> totalBenefit >= 10_000),
    STAR("스타", (totalBenefit) -> totalBenefit >= 5_000),
    NO_BADGE("없음", (totalBenefit) -> true);

    private final String name;
    private final Function<Integer, Boolean> condition;

    EventBadge(String name, Function<Integer, Boolean> condition) {
        this.name = name;
        this.condition = condition;
    }

    public static EventBadge from(EventResult result) {
        return Arrays.stream(values())
                .filter(badge -> badge.condition.apply(result.totalBenefit()))
                .findFirst()
                .orElse(NO_BADGE);
    }

    @Override
    public String toString() {
        return name;
    }
}