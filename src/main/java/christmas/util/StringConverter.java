package christmas.util;

import java.util.HashMap;

public class StringConverter {

    public static int convertToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    public static HashMap<String, Integer> convertToMap(String input) {
        validateInput(input);
        String[] items = input.split(",");
        HashMap<String, Integer> menu = new HashMap<>();

        for (String item : items) {
            String[] details = item.split("-");
            menu.put(details[0], Integer.parseInt(details[1]));
        }
        return menu;
    }

    private static void validateInput(String input) {
        if (!input.matches("^([가-힣]+-\\d+,)*([가-힣]+-\\d+)$")) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }
}
