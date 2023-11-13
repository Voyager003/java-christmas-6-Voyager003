package christmas.controller;

import christmas.config.PlannerConfig;
import christmas.dao.MenuRepository;
import christmas.service.MenuService;
import christmas.view.OutputView;

public class Planner {
    private final MenuService menuService = new MenuService(MenuRepository.getInstance());

    public void start() {
        initPlanner();
    }

    private void initPlanner() {
        new PlannerConfig(menuService);
        OutputView.printMessageIntroduce();
    }
}
