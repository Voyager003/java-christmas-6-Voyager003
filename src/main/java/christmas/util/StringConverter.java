package christmas.util;

public class StringConverter {

    public static int convertToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자가 아닌 문자가 포함되었거나 공백이 감지되었습니다.");
        }
    }
}
