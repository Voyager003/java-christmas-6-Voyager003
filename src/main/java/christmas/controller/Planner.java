package christmas.controller;

import christmas.config.PlannerConfig;
import christmas.dao.MenuRepository;
import christmas.domain.Order;
import christmas.domain.SelectionMenu;
import christmas.domain.date.VisitDate;
import christmas.service.MenuService;
import christmas.service.SelectionMenuService;
import christmas.util.StringConverter;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.HashMap;

public class Planner {
    private final MenuService menuService = new MenuService(MenuRepository.getInstance());
    private final SelectionMenuService selectionMenuService = new SelectionMenuService();

    public void start() {
        initPlanner();
        generateOrder();
    }

    private void initPlanner() {
        new PlannerConfig(menuService);
        OutputView.printMessageIntroduce();
    }

    private Order generateOrder() {
        VisitDate date = getVisitDate();
        SelectionMenu menu = getSelectionMenu();
        return new Order(date, menu);
    }

    private VisitDate getVisitDate() {
        try {
            String visitInput = InputView.inputVisitDate();
            return new VisitDate(visitInput);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getVisitDate();
        }
    }

    private SelectionMenu getSelectionMenu() {
        try {
            String menuInput = InputView.inputMenu();
            HashMap<String, Integer> inputs = StringConverter.convertToMap(menuInput);
            return selectionMenuService.saveSelectionMenu(inputs);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getSelectionMenu();
        }
    }
}
