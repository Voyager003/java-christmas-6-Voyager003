package christmas.domain;

import christmas.discount.*;

import java.util.HashMap;
import java.util.Map;

public class Benefit {
    private int benefitAmount;
    private Map<String, Integer> benefits = new HashMap<>();

    public Benefit(Order order) {
        calculateBenefitAmount(order);
    }

    public void calculateBenefitAmount(Order order) {
        benefits.put("크리스마스 디데이 할인", getChristmasDiscount(order));
        benefits.put("평일 할인", getWeekdayDiscount(order));
        benefits.put("주말 할인", getWeekendDiscount(order));
        benefits.put("특별 할인", getSpecialDiscount(order));
    }

    public int getTotalBenefitAmount() {
        return benefits.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
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
