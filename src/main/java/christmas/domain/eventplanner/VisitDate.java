package christmas.domain.eventplanner;

import static christmas.domain.eventplanner.EventCalendar.MONTH;
import static christmas.domain.eventplanner.EventCalendar.YEAR;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class VisitDate {

    private final LocalDate date;

    private VisitDate(int day) {
        this.date = LocalDate.of(YEAR, MONTH, day);
    }

    public static VisitDate from(int day) {
        return new VisitDate(day);
    }

    public int getDay() {
        return date.getDayOfMonth();
    }

    public DayOfWeek getDayOfWeek() {
        return date.getDayOfWeek();
    }
}
