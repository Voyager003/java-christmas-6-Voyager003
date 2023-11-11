package christmas.dao;

import christmas.domain.menu.Menu;

import java.util.HashMap;
import java.util.Map;

public class MenuRepository {

    private final Map<String, Menu> menu = new HashMap<>();

    public void saveMenu(Menu menuCategory) {
        menu.put(menuCategory.getName(), menuCategory);
    }

    public Menu findByName(String name) {
        return menu.get(name);
    }

    public Map<String, Menu> getMenu() {
        return menu;
    }
}
