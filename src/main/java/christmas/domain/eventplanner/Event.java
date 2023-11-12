package christmas.domain.eventplanner;

public enum Event {
    X_MAS_D_DAY("크리스마스 디데이 할인"),
    WEEKDAY("평일 할인"),
    WEEKEND("주말 할인"),
    SPECIAL("스페셜 할인"),
    GIFT("증정 이벤트");

    private final String name;

    Event(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
