package christmas.controller;

import christmas.config.PlannerConfig;
import christmas.dao.MenuRepository;
import christmas.domain.benefit.Benefit;
import christmas.domain.discount.*;
import christmas.domain.order.Order;
import christmas.domain.order.SelectionMenu;
import christmas.domain.order.date.VisitDate;
import christmas.domain.menu.Menu;
import christmas.service.MenuService;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Planner {
    public void start() {
        initPlanner();
        Order order = generateOrder();
        printBeforeApplyBenefit(order);
        Benefit benefit = generateBenefit(order);
        printAfterApplyBenefit(benefit, order);
    }

    private void initPlanner() {
        MenuService menuService = new MenuService(MenuRepository.getInstance());
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
            return new SelectionMenu(menuInput);
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

    private Benefit generateBenefit(Order order) {
        List<DiscountPolicy> policies =
                Arrays.asList(new ChristmasDiscountPolicy(),
                        new WeekdayDiscountPolicy(),
                        new WeekendDiscountPolicy(),
                        new SpecialDiscountPolicy());
        Benefit benefit = new Benefit(order, policies);
        OutputView.printGift(benefit);
        return benefit;
    }

    private void printAfterApplyBenefit(Benefit benefit, Order order) {
        OutputView.printBenefitDetail(benefit);
        OutputView.printTotalDetail(benefit);
        OutputView.printAfterDiscount(benefit, order);
        OutputView.printEventBadge(benefit);
    }
}
