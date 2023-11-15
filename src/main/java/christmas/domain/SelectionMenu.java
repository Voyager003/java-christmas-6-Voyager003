package christmas.domain;


import christmas.dao.MenuRepository;
import christmas.domain.menu.Beverage;
import christmas.domain.menu.Menu;
import christmas.util.StringConverter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SelectionMenu {
    private final MenuRepository menuRepository = MenuRepository.getInstance();
    private final Map<Menu, Integer> selectionMenu = new HashMap<>();

    public SelectionMenu(String input) {
        validateSelectionMenu(input);
        generateSelectionMenu(input);
    }

    public Map<Menu, Integer> getSelectionMenu() {
        return selectionMenu;
    }

    private void validateSelectionMenu(String input) {
        validateMenuName(input);
        validateQuantity(input);
        validateDuplicateMenu(input);
        validateMaximumMenu(input);
        validateOnlyContainBeverage(input);
    }

    private void validateMenuName(String input) {
        HashMap<String, Integer> menu = StringConverter.convertToMap(input);
        for (String menuName : menu.keySet()) {
            if (menuRepository.findByName(menuName) == null) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
        }
    }

    private void validateQuantity(String input) {
        HashMap<String, Integer> menu = StringConverter.convertToMap(input);
        for (int quantity : menu.values()) {
            if (quantity < 1) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
        }
    }

    private void validateDuplicateMenu(String input) {
        String[] menus = input.split(",");
        long uniqueMenuCount = Arrays.stream(menus).distinct().count();
        if (menus.length != uniqueMenuCount) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private void validateMaximumMenu(String input) {
        HashMap<String, Integer> menu = StringConverter.convertToMap(input);
        int totalCount = menu.values()
                .stream().mapToInt(Integer::intValue).sum();
        if (totalCount > 20) {
            throw new IllegalArgumentException("[ERROR] 메뉴의 총 주문 갯수가 20개를 초과했습니다. 다시 입력해 주세요.");
        }
    }

    public void validateOnlyContainBeverage(String input) {
        HashMap<String, Integer> menu = StringConverter.convertToMap(input);

        boolean isAllBeverages = menu.keySet().stream()
                .allMatch(menuName -> Arrays.stream(Beverage.values())
                        .map(Beverage::getName)
                        .anyMatch(beverageName -> beverageName.equals(menuName)));

        if (isAllBeverages) {
            throw new IllegalArgumentException("[ERROR] 음료만 주문할 수 없습니다. 다시 입력해 주세요.");
        }
    }

    private void generateSelectionMenu(String input) {
        HashMap<String, Integer> menu = StringConverter.convertToMap(input);
        for (Map.Entry<String, Integer> entry : menu.entrySet()) {
            String menuName = entry.getKey();
            int quantity = entry.getValue();
            Menu searchMenu = menuRepository.findByName(menuName);
            selectionMenu.put(searchMenu, quantity);
        }
    }
}
