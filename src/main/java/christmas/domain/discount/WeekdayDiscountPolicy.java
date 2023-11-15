package christmas.domain.discount;

import christmas.domain.order.Order;
import christmas.domain.order.date.Week;
import christmas.domain.menu.Dessert;
import christmas.domain.menu.Menu;

import java.util.Map;

public class WeekdayDiscountPolicy implements DiscountPolicy {

    private static final int DISCOUNT_PER_DESSERT = 2023;
    int dessertCount;

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
        for (Map.Entry<Menu, Integer> entry : selectionMenu.entrySet()) {
            Menu menu = entry.getKey();
            if (menu instanceof Dessert) {
                dessertCount += entry.getValue();
            }
        }
        return dessertCount;
    }
}
