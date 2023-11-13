package christmas.controller;

import christmas.config.PlannerConfig;
import christmas.dao.MenuRepository;
import christmas.domain.Order;
import christmas.domain.SelectionMenu;
import christmas.domain.date.VisitDate;
import christmas.domain.menu.Menu;
import christmas.service.MenuService;
import christmas.service.SelectionMenuService;
import christmas.util.StringConverter;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.HashMap;
import java.util.Map;


public class Planner {
    private final MenuService menuService = new MenuService(MenuRepository.getInstance());
    private final SelectionMenuService selectionMenuService = new SelectionMenuService();

    public void start() {
        initPlanner();
        Order order = generateOrder();
        printBeforeApplyBenefit(order);
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

    private void printBeforeApplyBenefit(Order order) {
        OutputView.printMessageEvent(order);
        printMenu(order);
        printBeforeDiscount(order);
    }

    private void printMenu(Order order) {
        OutputView.printMenuHeader();
        Map<Menu, Integer> selectionMenu = order.getSelectionMenu().getSelectionMenu();
        for (Map.Entry<Menu, Integer> entry : selectionMenu.entrySet()) {
            Menu menu = entry.getKey();
            int count = entry.getValue();
            OutputView.printMenu(menu.getName(), count);
        }
    }

    private void printBeforeDiscount(Order order) {
        int orderAmount = order.getOrderAmount();
        OutputView.printBeforeDiscount(orderAmount);
    }
}
