package christmas.domain.eventplanner.dto;

public class EventResult {

    private final int christmasBenefit;
    private final int weekdayBenefit;
    private final int weekendBenefit;
    private final int giftBenefit;
    private final int specialBenefit;

    private EventResult(
            int giftBenefit,
            int christmasBenefit,
            int weekdayBenefit,
            int weekendBenefit,
            int specialBenefit
    ) {
        this.giftBenefit = giftBenefit;
        this.christmasBenefit = christmasBenefit;
        this.weekdayBenefit = weekdayBenefit;
        this.weekendBenefit = weekendBenefit;
        this.specialBenefit = specialBenefit;
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

    public int getGiftBenefit() {
        return giftBenefit;
    }

    public int getChristmasBenefit() {
        return christmasBenefit;
    }

    public int getWeekdayBenefit() {
        return weekdayBenefit;
    }

    public int getWeekendBenefit() {
        return weekendBenefit;
    }

    public int getSpecialBenefit() {
        return specialBenefit;
    }

    public boolean hasBenefit() {
        return (christmasBenefit + weekdayBenefit + weekendBenefit + giftBenefit + specialBenefit) > 0;
    }
}
