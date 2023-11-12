package christmas.service;

import christmas.dao.MenuRepository;
import christmas.domain.SelectionMenu;
import christmas.domain.menu.Menu;

import java.util.HashMap;
import java.util.Map;


public class SelectionMenuService {
    private final MenuRepository menuRepository = MenuRepository.getInstance();
    private final SelectionMenu selectionMenu = new SelectionMenu();

    public SelectionMenu saveSelectionMenu(HashMap<String, Integer> input) {
        for (Map.Entry<String, Integer> entry : input.entrySet()) {
            String menuName = entry.getKey();
            int quantity = entry.getValue();
            Menu menu = menuRepository.findByName(menuName);
            selectionMenu.saveMenu(menu, quantity);
        }
        return selectionMenu;
    }
}