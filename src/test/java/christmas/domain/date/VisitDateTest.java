package christmas.domain.date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;


class VisitDateTest {

    @Test
    @DisplayName("올바른 조건의 입력이 주어진다면, 예외를 던지지 않는다.")
    void validateVisitDate_doesNotThrow() {
        /**
         * given : 올바른 조건인 1이 주어진다.
         * when : VisitDate 인스턴스를 생성한다.
         * then : 예외를 발생시키지 않는다.
         */
        String input = "1";

        assertThatCode(() -> new VisitDate(input))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("유효하지 않은 조건의 입력이 주어진다면, 예외를 던진다.")
    void validateVisitDate_throwException() {
        /**
         * given : 입력 조건(1~31)을 벗어난 수 36이 주어진다.
         * when : VisitDate 인스턴스를 생성한다.
         * then : IllegalArgumentException을 발생시킨다.
         */
        String input = "36";

        assertThatCode(() -> new VisitDate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    @Test
    @DisplayName("주어진 날짜에 해당하는 요일을 반환하는 기능을 테스트한다.")
    void getDayOfWeekFromVisitDate_returnFriday() {
        /**
         * given : 입력으로 1(일)이 주어진다.
         * when : VisitDate 인스턴스를 생성한다.
         * then : 1일은 금요일이므로, 금요일을 반환한다.
         */
        String input = "1";

        VisitDate visitDate = new VisitDate(input);

        assertThat(visitDate.getWeek()).isEqualTo(Week.FRIDAY);
    }

    @Test
    @DisplayName("주어진 날짜에 해당하는 요일을 반환하는 기능을 테스트한다.")
    void getDayOfWeekFromVisitDate_returnMonday() {
        /**
         * given : 입력으로 25(일)가 주어진다.
         * when : VisitDate 인스턴스를 생성한다.
         * then : 25일은 월요일이므로, 월요일을 반환한다.
         */
        String input = "25";

        VisitDate visitDate = new VisitDate(input);

        assertThat(visitDate.getWeek()).isEqualTo(Week.MONDAY);
    }
}