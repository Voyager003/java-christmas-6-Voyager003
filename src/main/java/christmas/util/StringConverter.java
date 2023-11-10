package christmas.util;

import java.util.HashMap;

public class StringConverter {

    public static int convertToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자가 아닌 문자가 포함되었거나 공백이 감지되었습니다.");
        }
    }

    public static HashMap<String, Integer> convertToMap(String input) {
        if (!input.matches("^([a-zA-Z가-힣0-9]+-\\d+,)*([a-zA-Z가-힣0-9]+-\\d+)$")) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
        String[] items = input.split(",");
        HashMap<String, Integer> menu = new HashMap<>();

        for (String item : items) {
            String[] details = item.split("-");
            menu.put(details[0], Integer.parseInt(details[1]));
        }
        return menu;
    }
}
