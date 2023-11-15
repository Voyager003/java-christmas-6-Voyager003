package christmas.view;

import christmas.domain.benefit.Badge;
import christmas.domain.benefit.Benefit;
import christmas.domain.order.Order;

import java.text.NumberFormat;
import java.util.Map;

public class OutputView {

    private static final String MESSAGE_NOTHING = "없음";
    private static final String MESSAGE_INTRODUCE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String MESSAGE_INPUT_DATE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String MESSAGE_INPUT_MENU = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";
    private static final String MESSAGE_MENU_HEADER = "\n<주문 메뉴>";
    private static final String MESSAGE_BEFORE_DISCOUNT = "\n<할인 전 총주문 금액>";
    private static final String MESSAGE_GIFT = "\n<증정 메뉴>";
    private static final String MESSAGE_BENEFIT_DETAIL = "\n<혜택 내역>";
    private static final String MESSAGE_TOTAL_DETAIL = "\n<총혜택 금액>";
    private static final String MESSAGE_AFTER_DISCOUNT = "\n<할인 후 예상 결제 금액>";
    private static final String MESSAGE_EVENT_BADGE = "\n<12월 이벤트 배지>";

    public static void printMessageIntroduce() {
        System.out.println(MESSAGE_INTRODUCE);
    }

    public static void printMessageInputDate() {
        System.out.println(MESSAGE_INPUT_DATE);
    }

    public static void printMessageInputMenu() {
        System.out.println(MESSAGE_INPUT_MENU);
    }

    public static void printMessageEvent(Order order) {
        String date = order.getVisitDate().toString();
        System.out.println("12월 " + date + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
    }

    public static void printMenuHeader() {
        System.out.println(MESSAGE_MENU_HEADER);
    }

    public static void printMenu(String menuName, int count) {
        System.out.printf("%s %d개\n", menuName, count);
    }

    public static void printBeforeDiscount(int orderAmount) {
        System.out.println(MESSAGE_BEFORE_DISCOUNT);
        System.out.println(NumberFormat.getInstance().format(orderAmount) + "원");
    }

    public static void printGift(Benefit benefit) {
        System.out.println(MESSAGE_GIFT);
        Map<String, Integer> benefits = benefit.getBenefits();
        String giftMenu = MESSAGE_NOTHING;
        if (benefits.get("증정 이벤트") > 0) {
            giftMenu = "샴페인 1개";
        }
        System.out.println(giftMenu);
    }

    public static void printBenefitDetail(Benefit benefit) {
        System.out.println(MESSAGE_BENEFIT_DETAIL);
        Map<String, Integer> benefits = benefit.getBenefits();
        boolean hasBenefit = false;
        for (Map.Entry<String, Integer> entry : benefits.entrySet()) {
            if (entry.getValue() > 0) {
                hasBenefit = true;
                System.out.println(entry.getKey() + ": -" +
                        NumberFormat.getInstance().format(entry.getValue()) + "원");
            }
        }
        if (!hasBenefit) {
            System.out.println(MESSAGE_NOTHING);
        }
    }

    public static void printTotalDetail(Benefit benefit) {
        System.out.println(MESSAGE_TOTAL_DETAIL);
        int amount = benefit.getTotalBenefitAmount();
        String formattedAmount = NumberFormat.getInstance().format(amount);
        if (amount != 0) {
            formattedAmount = "-" + formattedAmount;
        }
        System.out.println(formattedAmount + "원");
    }

    public static void printAfterDiscount(int totalDiscountAmount) {
        System.out.println(MESSAGE_AFTER_DISCOUNT);
        System.out.println(NumberFormat.getInstance().format(totalDiscountAmount) + "원");
    }

    public static void printEventBadge(Benefit benefit) {
        System.out.println(MESSAGE_EVENT_BADGE);
        Badge badge = benefit.getBadge();
        System.out.println(badge.toString());
    }
}
