package christmas.domain;

import christmas.discount.ChristmasDiscountPolicy;
import christmas.discount.DiscountPolicy;
import christmas.discount.WeekdayDiscountPolicy;
import christmas.domain.date.VisitDate;
import christmas.domain.menu.Menu;

import java.util.Map;


public class Order {

    final VisitDate visitDate;
    final SelectionMenu selectionMenu;
    int orderAmount;
    DiscountPolicy discountPolicy;

    public Order(VisitDate visitDate, SelectionMenu selectionMenu) {
        this.visitDate = visitDate;
        this.selectionMenu = selectionMenu;
        calculateOrderAmount();
    }

    private void calculateOrderAmount() {
        Map<Menu, Integer> menus = selectionMenu.getSelectionMenu();
        for (Map.Entry<Menu, Integer> entry : menus.entrySet()) {
            Menu menu = entry.getKey();
            int quantity = entry.getValue();
            this.orderAmount += menu.getPrice() * quantity;
        }
    }

    public int getChristmasDiscount() {
        discountPolicy = new ChristmasDiscountPolicy();
        return discountPolicy.discount(this);
    }

    public int getWeekdayDiscount() {
        discountPolicy = new WeekdayDiscountPolicy();
        return discountPolicy.discount(this);
    }

    public VisitDate getVisitDate() {
        return visitDate;
    }

    public SelectionMenu getSelectionMenu() {
        return selectionMenu;
    }

    public int getOrderAmount() {
        return orderAmount;
    }
}


