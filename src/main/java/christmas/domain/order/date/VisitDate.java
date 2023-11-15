package christmas.domain.order.date;

import christmas.util.StringConverter;

import java.util.Arrays;

public class VisitDate {

    private final int visitDate;
    private final Week week;

    public VisitDate(String input) {
        int dateInput = StringConverter.convertToInt(input);
        validateVisitDate(dateInput);
        this.visitDate = dateInput;
        this.week = getDayOfWeekFromVisitDate(dateInput);
    }

    private void validateVisitDate(int visitDate) {
        if (visitDate < 1 || visitDate > 31) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    private Week getDayOfWeekFromVisitDate(int visitDate) {
        int dayIndex = visitDate % 7;
        return Arrays.stream(Week.values())
                .filter(week -> week.getIndex() == dayIndex)
                .findFirst()
                .orElse(Week.THURSDAY);
    }

    public int getVisitDate() {
        return visitDate;
    }

    public Week getWeek() {
        return week;
    }

    @Override
    public String toString() {
        return String.valueOf(visitDate);
    }
}
