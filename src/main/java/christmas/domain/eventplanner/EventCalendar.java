package christmas.domain.eventplanner;

import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;

import java.time.DayOfWeek;
import java.time.Month;
import java.util.Set;

public class EventCalendar {

    private static final Set<DayOfWeek> WEEKDAYS = Set.of(SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY);
    private static final Set<DayOfWeek> WEEKENDS = Set.of(FRIDAY, SATURDAY);
    private static final Set<Integer> SPECIAL_DAYS = Set.of(3, 10, 17, 24, 25, 31);

    public static final int EVENT_YEAR = 2023;
    public static final int EVENT_MONTH = Month.DECEMBER.getValue();

    public static final int X_MAS_EVENT_FIRST_DAY = 1;
    public static final int X_MAS_EVENT_LAST_DAY = 25;

    public static boolean isXMasDDay(VisitDate visitDate) {
        int day = visitDate.getDay();
        return X_MAS_EVENT_FIRST_DAY <= day && day <= X_MAS_EVENT_LAST_DAY;
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
