package christmas.domain.benefit;

import christmas.domain.order.Order;

import static christmas.domain.menu.Beverage.CHAMPAGNE;

public class Gift {
    private static final int GIFT_EVENT_AMOUNT = 120_000;
    private static final String GIFT_EVENT_NAME = "증정 이벤트";
    private static final String GIFT_MENU_NAME = "샴페인";
    private static final int GIFT_MENU_PRICE = CHAMPAGNE.getPrice();
    private String giftMenuName = null;

    public void grant(Order order) {
        if (order.getOrderAmount() >= GIFT_EVENT_AMOUNT) {
            giftMenuName = GIFT_MENU_NAME;
        }
    }

    public BenefitDetail getBenefitDetail() {
        if (giftMenuName == null) {
            return new BenefitDetail(GIFT_EVENT_NAME, 0);
        }
        return new BenefitDetail(GIFT_EVENT_NAME, GIFT_MENU_PRICE);
    }

    public String getGiftMenuName() {
        return giftMenuName;
    }
}
