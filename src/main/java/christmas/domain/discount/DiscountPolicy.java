package christmas.domain.discount;

import christmas.domain.benefit.BenefitDetail;
import christmas.domain.order.Order;

public interface DiscountPolicy {
    BenefitDetail applyDiscount(Order order);
}
