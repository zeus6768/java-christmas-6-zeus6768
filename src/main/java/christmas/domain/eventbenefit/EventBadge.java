package christmas.domain.eventbenefit;

import java.util.Arrays;

import christmas.domain.eventplanner.EventPlanResult;

public enum EventBadge {
    SANTA("산타", 20_000),
    TREE("트리", 10_000),
    STAR("별", 5_000),
    NO_BADGE("", 0);

    private final String name;
    private final int minTotalBenefit;

    EventBadge(String name, int minTotalBenefit) {
        this.name = name;
        this.minTotalBenefit = minTotalBenefit;
    }

    public static EventBadge from(EventPlanResult result) {
        return Arrays.stream(values())
                .filter(badge -> badge.isQualified(result.getTotalBenefitAmount()))
                .findFirst()
                .orElse(NO_BADGE);
    }

    public String getName() {
        return name;
    }

    private boolean isQualified(int totalBenefit) {
        return minTotalBenefit <= totalBenefit;
    }
}
