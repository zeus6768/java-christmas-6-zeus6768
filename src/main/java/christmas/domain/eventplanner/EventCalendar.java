package christmas.domain.eventplanner;

import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;

import java.time.DayOfWeek;
import java.util.Set;

public class EventCalendar {

    private static final Set<DayOfWeek> WEEKDAYS = Set.of(SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY);
    private static final Set<DayOfWeek> WEEKENDS = Set.of(FRIDAY, SATURDAY);
    private static final Set<Integer> SPECIAL_DAYS = Set.of(3, 10, 17, 24, 25, 31);

    public static final int YEAR = 2023;
    public static final int MONTH = 12;
    public static final int FIRST_DAY_OF_MONTH = 1;
    public static final int LAST_DAY_OF_MONTH = 31;

    public static final int CHRISTMAS_EVENT_FIRST_DAY = FIRST_DAY_OF_MONTH;
    public static final int CHRISTMAS_EVENT_LAST_DAY = 25;

    public static boolean isChristmasDDay(VisitDate visitDate) {
        int date = visitDate.getDay();
        return CHRISTMAS_EVENT_FIRST_DAY <= date && date <= CHRISTMAS_EVENT_LAST_DAY;
    }

    public static boolean isWeekday(VisitDate visitDate) {
        return WEEKDAYS.contains(visitDate.getDayOfWeek());
    }

    public static boolean isWeekEnd(VisitDate visitDate) {
        return WEEKENDS.contains(visitDate.getDayOfWeek());
    }

    public static boolean isSpecialDay(VisitDate visitDate) {
        return SPECIAL_DAYS.contains(visitDate.getDay());
    }
}
