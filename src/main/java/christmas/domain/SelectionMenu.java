package christmas.domain;


import java.util.HashMap;
import java.util.Map;

public class SelectionMenu {

    private final Map<String, Integer> selectionMenu = new HashMap<>();

    public void saveMenu(String menuName, int quantity) {
        selectionMenu.put(menuName, quantity);
    }

    public void findByName(String menuName) {
        selectionMenu.get(menuName);
    }

    public Map<String, Integer> getSelectionMenu() {
        return selectionMenu;
    }
}
