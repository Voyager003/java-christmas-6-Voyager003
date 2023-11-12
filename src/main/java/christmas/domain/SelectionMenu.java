package christmas.domain;


import christmas.domain.menu.Menu;

import java.util.HashMap;
import java.util.Map;

public class SelectionMenu {

    private final Map<Menu, Integer> selectionMenu = new HashMap<>();

    public void saveMenu(Menu menuName, int quantity) {
        selectionMenu.put(menuName, quantity);
    }

    public Map<Menu, Integer> getSelectionMenu() {
        return selectionMenu;
    }
}
