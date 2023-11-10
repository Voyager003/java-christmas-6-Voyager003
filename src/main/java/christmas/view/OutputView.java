package christmas.view;

public class OutputView {

    public static void printMessageIntroduce() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public static void printMessageInputDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
    }

    public static void printMessageInputMenu() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
    }

    public static void printMessageEvent() {
        System.out.println("12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
    }

    public static void printMenuHeader() {
        System.out.println("<주문 메뉴>");
    }

    public static void printBeforeDiscount() {
        System.out.println("<할인 전 총주문 금액>");
    }

    public static void printGiftMenu() {
        System.out.println("<증정 메뉴>");
    }

    public static void printBenefitDetail() {
        System.out.println("<혜택 내역>");
    }

    public static void printTotalDetail() {
        System.out.println("<총혜택 금액>");
    }
    
    public static void printAfterDiscount() {
        System.out.println("<할인 후 예상 결제 금액>");
    }

    public static void printEventBadge() {
        System.out.println("산타");
    }
}
