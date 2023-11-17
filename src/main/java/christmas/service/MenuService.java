package christmas.service;

import christmas.dao.MenuRepository;
import christmas.domain.menu.Menu;

import java.util.Map;

public class MenuService {

    private final MenuRepository menuRepository;

    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public void saveMenu(Menu menuCategory) {
        menuRepository.saveMenu(menuCategory);
    }

    public Menu findByName(String name) {
        return menuRepository.findByName(name);
    }

    public int getMenuSize() {
        Map<String, Menu> menu = menuRepository.getMenu();
        return menu.size();
    }
}
