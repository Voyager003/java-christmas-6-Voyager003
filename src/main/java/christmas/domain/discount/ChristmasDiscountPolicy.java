package christmas.domain.discount;

import christmas.domain.order.Order;

public class ChristmasDiscountPolicy implements DiscountPolicy {
    private static final int BASE_DISCOUNT = 1000;
    private static final int ADDITIONAL_DISCOUNT_PER_DAY = 100;
    private static final int EVENT_START = 1;
    private static final int EVENT_END = 25;

    private int discountAmount;

    @Override
    public int discount(Order order) {
        int visitDate = order.getVisitDate().getVisitDate();
        if (visitDate < EVENT_START || visitDate > EVENT_END) {
            return 0;
        }
        int daysFromStart = visitDate - EVENT_START;
        return discountAmount = BASE_DISCOUNT + (daysFromStart * ADDITIONAL_DISCOUNT_PER_DAY);
    }

    @Override
    public String toString() {
        return discountAmount + "원";
    }
}
