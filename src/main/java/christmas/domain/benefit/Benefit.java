package christmas.domain.benefit;


import christmas.domain.discount.*;
import christmas.domain.order.Order;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static christmas.domain.menu.Beverage.CHAMPAGNE;
import static java.util.Comparator.comparing;

public class Benefit {
    private static final int GIFT_EVENT_AMOUNT = 120_000;
    private Map<String, Integer> benefits = new HashMap<>();
    private Badge badge;

    public Benefit(Order order) {
        calculateBenefitAmount(order);
        badge = grantBadge();
    }

    public int getTotalBenefitAmount() {
        return benefits.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public int calculateTotalDiscount(Order order) {
        if (order.getOrderAmount() < 10_000) {
            return order.getOrderAmount();
        }
        int totalDiscountAmount = getTotalBenefitAmount() - benefits.get("증정 이벤트");
        return order.getOrderAmount() - totalDiscountAmount;
    }

    public Map<String, Integer> getBenefits() {
        return benefits;
    }

    public Badge getBadge() {
        return badge;
    }

    private void calculateBenefitAmount(Order order) {
        benefits.put("크리스마스 디데이 할인", getChristmasDiscount(order));
        benefits.put("평일 할인", getWeekdayDiscount(order));
        benefits.put("주말 할인", getWeekendDiscount(order));
        benefits.put("특별 할인", getSpecialDiscount(order));
        grantGiftEvent(order);
    }

    private void grantGiftEvent(Order order) {
        int eventBenefit = 0;
        if (order.getOrderAmount() >= GIFT_EVENT_AMOUNT) {
            eventBenefit = CHAMPAGNE.getPrice();
        }
        benefits.put("증정 이벤트", eventBenefit);
    }

    private Badge grantBadge() {
        int totalBenefitAmount = getTotalBenefitAmount();
        return Arrays.stream(Badge.values())
                .sorted(comparing(Badge::getPrice).reversed())
                .filter(badge -> badge.isPriceGreaterOrEqual(totalBenefitAmount))
                .findFirst()
                .orElse(Badge.NONE);
    }

    private int getChristmasDiscount(Order order) {
        DiscountPolicy christmasDiscountPolicy = new ChristmasDiscountPolicy();
        return christmasDiscountPolicy.discount(order);
    }

    private int getWeekdayDiscount(Order order) {
        DiscountPolicy weekdayDiscountPolicy = new WeekdayDiscountPolicy();
        return weekdayDiscountPolicy.discount(order);
    }

    private int getWeekendDiscount(Order order) {
        DiscountPolicy weekendDiscountPolicy = new WeekendDiscountPolicy();
        return weekendDiscountPolicy.discount(order);
    }

    private int getSpecialDiscount(Order order) {
        DiscountPolicy specialDiscountPolicy = new SpecialDiscountPolicy();
        return specialDiscountPolicy.discount(order);
    }
}
