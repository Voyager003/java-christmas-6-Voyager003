package christmas.service;

import christmas.dao.MenuRepository;
import christmas.domain.SelectionMenu;

import java.util.HashMap;
import java.util.Map;


public class SelectionMenuService {
    private final MenuRepository menuRepository = MenuRepository.getInstance();
    private final SelectionMenu selectionMenu = new SelectionMenu();

    public void saveSelectionMenu(HashMap<String, Integer> input) {
        for (Map.Entry<String, Integer> entry : input.entrySet()) {
            String menuName = entry.getKey();
            int quantity = entry.getValue();
            validateSelectionMenu(menuName, quantity);
            selectionMenu.saveMenu(menuName, quantity);
        }
    }

    private void validateSelectionMenu(String menuName, int quantity) {
        validateIsExist(menuName);
        validateMenuQuantity(quantity);
//        validateDuplicateMenu(menuName, quantity);
    }

    private void validateIsExist(String menuName) {
        if (!menuRepository.exists(menuName)) {
            throw new IllegalArgumentException("[ERROR] 존재하지 않는 메뉴입니다. 다시 입력해 주세요.");
        }
    }

    private void validateMenuQuantity(int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException("[ERROR] 주문 수량을 1 이상의 수로 다시 입력해 주세요.");
        }
    }
//
//    private void validateDuplicateMenu(String menuName, int quantity) {
//        if (selectionMenu.getSelectionMenu().containsKey(menuName)) {
//            throw new IllegalArgumentException("[ERROR] 중복된 메뉴는 입력될 수 없습니다. 다시 입력해 주세요.");
//        }
//    }
}