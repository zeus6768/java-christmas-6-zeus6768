package christmas.domain;

public class VisitDate {

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
        return date < 1 || date > 31;
    }

    public int getDate() {
        return date;
    }
}
