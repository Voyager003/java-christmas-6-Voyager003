package christmas.domain.benefit;

import christmas.domain.discount.DiscountPolicy;
import christmas.domain.order.Order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Benefit {
    private List<BenefitDetail> benefits = new ArrayList<>();
    private Gift gift;
    private Badge badge;

    public Benefit(Order order, List<DiscountPolicy> policies) {
        applyDiscountPolicies(order, policies);
        applyGiftEvent(order);
        badge = grantBadge();
    }

    public int calculateTotalDiscount(Order order) {
        if (order.getOrderAmount() < 10_000) {
            return order.getOrderAmount();
        }
        int totalDiscountAmount = getTotalBenefitAmount() - getGiftEventAmount();
        return order.getOrderAmount() - totalDiscountAmount;
    }

    public int getTotalBenefitAmount() {
        return benefits.stream().mapToInt(BenefitDetail::getAmount).sum();
    }

    public List<BenefitDetail> getBenefits() {
        return benefits;
    }

    public Badge getBadge() {
        return badge;
    }

    public String getGift() {
        return gift.getGiftMenuName();
    }

    private void applyDiscountPolicies(Order order, List<DiscountPolicy> policies) {
        policies.forEach(policy -> {
            BenefitDetail benefitDetail = policy.applyDiscount(order);
            if (benefitDetail.isNonZero()) {
                benefits.add(benefitDetail);
            }
        });
    }

    private int getGiftEventAmount() {
        return benefits.stream()
                .filter(benefit -> benefit.getName().equals("증정 이벤트"))
                .mapToInt(BenefitDetail::getAmount)
                .findFirst()
                .orElse(0);
    }

    private void applyGiftEvent(Order order) {
        gift = new Gift();
        gift.grant(order);
        BenefitDetail giftBenefitDetail = gift.getBenefitDetail();
        if (giftBenefitDetail.isNonZero()) {
            benefits.add(giftBenefitDetail);
        }
    }

    private Badge grantBadge() {
        int totalBenefitAmount = getTotalBenefitAmount();
        return Arrays.stream(Badge.values())
                .sorted(Comparator.comparing(Badge::getPrice).reversed())
                .filter(badge -> badge.isPriceGreaterOrEqual(totalBenefitAmount))
                .findFirst()
                .orElse(Badge.NONE);
    }
}
