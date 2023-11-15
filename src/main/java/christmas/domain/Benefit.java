package christmas.domain;

import christmas.discount.*;

import java.util.HashMap;
import java.util.Map;

import static christmas.domain.menu.Beverage.CHAMPAGNE;

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

    public void calculateBenefitAmount(Order order) {
        benefits.put("크리스마스 디데이 할인", getChristmasDiscount(order));
        benefits.put("평일 할인", getWeekdayDiscount(order));
        benefits.put("주말 할인", getWeekendDiscount(order));
        benefits.put("특별 할인", getSpecialDiscount(order));
        grantGiftEvent(order);
    }

    public int calculateTotalDiscount(Order order) {
        if (order.getOrderAmount() < 10_000) {
            return order.getOrderAmount();
        }
        int totalDiscountAmount = getTotalBenefitAmount() - benefits.get("증정 이벤트");
        return order.getOrderAmount() - totalDiscountAmount;
    }

    private void grantGiftEvent(Order order) {
        int eventBenefit = 0;
        if (order.getOrderAmount() >= GIFT_EVENT_AMOUNT) {
            eventBenefit = CHAMPAGNE.getPrice();
        }
        benefits.put("증정 이벤트", eventBenefit);
    }

    private Badge grantBadge() {
        int benefitAmount = getTotalBenefitAmount();
        if (benefitAmount >= Badge.SANTA.getPrice()) {
            return Badge.SANTA;
        }
        if (benefitAmount >= Badge.TREE.getPrice()) {
            return Badge.TREE;
        }
        if (benefitAmount >= Badge.STAR.getPrice()) {
            return Badge.STAR;
        }
        return Badge.NONE;
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

    public Map<String, Integer> getBenefits() {
        return benefits;
    }

    public Badge getBadge() {
        return badge;
    }
}
