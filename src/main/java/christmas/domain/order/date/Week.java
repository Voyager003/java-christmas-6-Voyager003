package christmas.domain.order.date;

public enum Week {
    SUNDAY("일", 3),
    MONDAY("월", 4),
    TUESDAY("화", 5),
    WEDNESDAY("수", 6),
    THURSDAY("목", 7),
    FRIDAY("금", 1),
    SATURDAY("토", 2);

    private final String day;
    private final int index;

    Week(String day, int index) {
        this.day = day;
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}

