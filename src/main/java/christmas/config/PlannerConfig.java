package christmas.config;


import christmas.domain.menu.Appetizer;
import christmas.domain.menu.Beverage;
import christmas.domain.menu.Dessert;
import christmas.domain.menu.MainDish;
import christmas.service.MenuService;

import java.util.Arrays;

public class PlannerConfig {

    private final MenuService menuService;

    public PlannerConfig(MenuService menuService) {
        this.menuService = menuService;
        initializeMenu();
    }

    private void initializeMenu() {
        Arrays.stream(MainDish.values()).forEach(menuService::saveMenu);
        Arrays.stream(Appetizer.values()).forEach(menuService::saveMenu);
        Arrays.stream(Beverage.values()).forEach(menuService::saveMenu);
        Arrays.stream(Dessert.values()).forEach(menuService::saveMenu);
    }
}
