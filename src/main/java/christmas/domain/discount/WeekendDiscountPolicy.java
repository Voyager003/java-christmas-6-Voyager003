package christmas.domain.discount;

import christmas.domain.order.Order;
import christmas.domain.order.date.Week;
import christmas.domain.menu.MainDish;
import christmas.domain.menu.Menu;

import java.util.Map;

public class WeekendDiscountPolicy implements DiscountPolicy {

    private static final int DISCOUNT_PER_MAINDISH = 2023;
    private int mainDishCount;

    @Override
    public int discount(Order order) {
        if (!isWeekend(order.getVisitDate().getWeek())) {
            return 0;
        }

        return containMainDish(order.getSelectionMenu().getSelectionMenu()) * DISCOUNT_PER_MAINDISH;
    }

    private boolean isWeekend(Week week) {
        return week.getIndex() == Week.FRIDAY.getIndex() || week.getIndex() == Week.SATURDAY.getIndex();
    }

    private int containMainDish(Map<Menu, Integer> selectionMenu) {
        for (Map.Entry<Menu, Integer> entry : selectionMenu.entrySet()) {
            Menu menu = entry.getKey();
            if (menu instanceof MainDish) {
                mainDishCount += entry.getValue();
            }
        }
        return mainDishCount;
    }
}
