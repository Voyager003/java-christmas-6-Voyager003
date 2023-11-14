package christmas.domain;


import christmas.dao.MenuRepository;
import christmas.domain.menu.Menu;

import java.util.HashMap;
import java.util.Map;

public class SelectionMenu {
    private final MenuRepository menuRepository = MenuRepository.getInstance();
    private final Map<Menu, Integer> selectionMenu = new HashMap<>();

    public SelectionMenu(HashMap<String, Integer> input) {
        generateSelectionMenu(input);
    }

    public Map<Menu, Integer> getSelectionMenu() {
        return selectionMenu;
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
