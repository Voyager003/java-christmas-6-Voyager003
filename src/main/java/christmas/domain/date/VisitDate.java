package christmas.domain.date;

import christmas.util.StringConverter;


public class VisitDate {

    private final int visitDate;

    public VisitDate(String input) {
        int dateInput = StringConverter.convertToInt(input);
        validateVisitDate(dateInput);
        this.visitDate = dateInput;
    }

    private void validateVisitDate(int visitDate) {
        if (visitDate < 1 || visitDate > 31) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    public int getVisitDate() {
        return visitDate;
    }
}
