package christmas.dao;

import christmas.domain.menu.Menu;

import java.util.HashMap;
import java.util.Map;

public class MenuRepository {
    private static MenuRepository instance = null;
    private final Map<String, Menu> menu = new HashMap<>();

    public static MenuRepository getInstance() {
        if (instance == null) {
            instance = new MenuRepository();
        }
        return instance;
    }

    public void saveMenu(Menu menuCategory) {
        menu.put(menuCategory.getName(), menuCategory);
    }

    public Menu findByName(String name) {
        return menu.get(name);
    }

    public Map<String, Menu> getMenu() {
        return menu;
    }

    public boolean exists(String menuName) {
        return menu.containsKey(menuName);
    }
}
