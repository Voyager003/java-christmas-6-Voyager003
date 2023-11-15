package christmas.domain.discount;

import christmas.domain.benefit.BenefitDetail;
import christmas.domain.order.Order;

public class ChristmasDiscountPolicy implements DiscountPolicy {
    private static final int BASE_DISCOUNT = 1000;
    private static final int ADDITIONAL_DISCOUNT_PER_DAY = 100;
    private static final int EVENT_START = 1;
    private static final int EVENT_END = 25;
    private int discountAmount;

    @Override
    public BenefitDetail applyDiscount(Order order) {
        int discountAmount = discount(order);
        return new BenefitDetail("크리스마스 디데이 할인", discountAmount);
    }

    private int discount(Order order) {
        int visitDate = order.getVisitDate().getVisitDate();
        if (visitDate < EVENT_START || visitDate > EVENT_END) {
            return 0;
        }
        int daysFromStart = visitDate - EVENT_START;
        return discountAmount = BASE_DISCOUNT + (daysFromStart * ADDITIONAL_DISCOUNT_PER_DAY);
    }
}
