package christmas.discount;

import christmas.domain.Order;
import christmas.domain.date.Week;
import christmas.domain.menu.Dessert;
import christmas.domain.menu.Menu;

import java.util.Map;

public class WeekdayDiscountPolicy implements DiscountPolicy {

    private static final int DISCOUNT_PER_DESSERT = 2023;
    private int dessertCount;
    private int discountAmount;

    @Override
    public int discount(Order order) {
        if (!isWeekDay(order.getVisitDate().getWeek())) {
            return 0;
        }

        return containDessert(order.getSelectionMenu().getSelectionMenu()) * DISCOUNT_PER_DESSERT;
    }

    private boolean isWeekDay(Week week) {
        return week.getIndex() >= Week.SUNDAY.getIndex() && week.getIndex() <= Week.THURSDAY.getIndex();
    }

    private int containDessert(Map<Menu, Integer> selectionMenu) {
        int dessertCount = 0;
        for (Map.Entry<Menu, Integer> entry : selectionMenu.entrySet()) {
            Menu menu = entry.getKey();
            if (menu instanceof Dessert) {
                dessertCount += entry.getValue();
            }
        }
        return dessertCount;
    }

    @Override
    public String toString() {
        return discountAmount + "원";
    }
}
