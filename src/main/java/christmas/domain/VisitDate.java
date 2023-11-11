package christmas.domain;

public class VisitDate {

    public static final int FIRST_DAY_OF_MONTH = 1;
    public static final int LAST_DAY_OF_MONTH = 31;

    private final int date;

    private VisitDate(int date) {
        validate(date);
        this.date = date;
    }

    public static VisitDate from(int date) {
        return new VisitDate(date);
    }

    private void validate(int date) {
        if (isOutOfRange(date)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean isOutOfRange(int date) {
        return date < FIRST_DAY_OF_MONTH || date > LAST_DAY_OF_MONTH;
    }

    public int getDate() {
        return date;
    }
}
