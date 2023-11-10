package christmas.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class StringConverterTest {

    @Test
    @DisplayName("문자열을 정수로 변환하는 기능에서 정수를 입력하면 예외를 던지지 않는다.")
    void convertToInt_doesNotThrow() {
        /**
         * given : 입력으로 정수인 1이 주어진다.
         * when : 문자열을 정수로 변환하는 기능을 실행한다.
         * then : 예외를 던지지 않는다.
         */
        String input = "1";
        assertThatCode(() -> StringConverter.convertToInt(input))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("문자열을 정수로 변환하는 기능에서 공백을 입력하면 예외를 던진다.")
    void convertToInt_throwException_blank() {
        /**
         * given : 입력으로 공백이 주어진다.
         * when : 문자열을 정수로 변환하는 기능을 실행한다.
         * then : IllegalArgumentException를 던진다.
         */
        String input = " ";
        assertThatCode(() -> StringConverter.convertToInt(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("숫자가 아닌 문자가 포함되었거나 공백이 감지되었습니다.");
    }

    @Test
    @DisplayName("문자열을 정수로 변환하는 기능에서 문자를 입력하면 예외를 던진다.")
    void convertToInt_throwException_String() {
        /**
         * given : 입력으로 문자가 섞인 정수가 주어진다.
         * when : 문자열을 정수로 변환하는 기능을 실행한다.
         * then : IllegalArgumentException를 던진다.
         */
        String input = "10j";
        assertThatCode(() -> StringConverter.convertToInt(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("숫자가 아닌 문자가 포함되었거나 공백이 감지되었습니다.");
    }

    @Test
    @DisplayName("문자열을 정수로 변환하고 예상한 값을 반환하는지 확인한다.")
    void convertToInt_validateInt() {
        /**
         * given : "1"이 입력된다.
         * when : 문자열을 정수로 변환하는 기능을 실행한다.
         * then : 반환한 값이 정수 1인지 확인하고, Integer 클래스의 인스턴스인지 확인한다.
         */
        int convertedInput = StringConverter.convertToInt("1");
        int expectedInput = 1;

        assertThat(convertedInput).isEqualTo(expectedInput);
        assertThat(convertedInput).isInstanceOf(Integer.class);
    }
}