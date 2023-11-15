package christmas.domain.discount;

import christmas.domain.benefit.BenefitDetail;
import christmas.domain.order.Order;

import java.util.List;

public class SpecialDiscountPolicy implements DiscountPolicy {

    private static final int DISCOUNT_PER_STAR = 1000;
    private List<Integer> specialDay = List.of(3, 10, 17, 24, 25, 31);
    private int discountAmount;

    @Override
    public BenefitDetail applyDiscount(Order order) {
        int discountAmount = discount(order);
        return new BenefitDetail("특별 할인", discountAmount);
    }

    private int discount(Order order) {
        int visitDate = order.getVisitDate().getVisitDate();
        if (specialDay.contains(visitDate)) {
            discountAmount = DISCOUNT_PER_STAR;
        }
        return discountAmount;
    }
}
