package christmas.discount;

import christmas.domain.Order;

public interface DiscountPolicy {
    int discount(Order order);
}
