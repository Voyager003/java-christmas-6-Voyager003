package christmas.domain;


import christmas.dao.MenuRepository;
import christmas.domain.menu.Menu;

import java.util.HashMap;
import java.util.Map;

public class SelectionMenu {
    private final MenuRepository menuRepository = MenuRepository.getInstance();
    private final Map<Menu, Integer> selectionMenu = new HashMap<>();

    public SelectionMenu(HashMap<String, Integer> input) {
        validateSelectionMenu(input);
        generateSelectionMenu(input);
    }

    public Map<Menu, Integer> getSelectionMenu() {
        return selectionMenu;
    }

    private void validateSelectionMenu(HashMap<String, Integer> input) {
        validateMenuName(input);
        validateQuantity(input);
    }

    private void validateMenuName(HashMap<String, Integer> input) {
        for (String menuName : input.keySet()) {
            if (menuRepository.findByName(menuName) == null) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
        }
    }

    private void validateQuantity(HashMap<String, Integer> input) {
        for (int quantity : input.values()) {
            if (quantity < 1) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
        }
    }

    private void generateSelectionMenu(HashMap<String, Integer> input) {
        for (Map.Entry<String, Integer> entry : input.entrySet()) {
            String menuName = entry.getKey();
            int quantity = entry.getValue();
            Menu menu = menuRepository.findByName(menuName);
            selectionMenu.put(menu, quantity);
        }
    }
}
