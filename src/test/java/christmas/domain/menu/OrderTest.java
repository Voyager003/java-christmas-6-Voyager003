package christmas.domain.menu;

import christmas.domain.Order;
import christmas.domain.SelectionMenu;
import christmas.domain.date.VisitDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static christmas.domain.menu.Beverage.CHAMPAGNE;
import static christmas.domain.menu.Beverage.RED_WINE;
import static christmas.domain.menu.Dessert.CHOCOLATE_CAKE;
import static christmas.domain.menu.MainDish.SEAFOOD_PASTA;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


class OrderTest {

    @Test
    @DisplayName("주문 금액을 계산하는 기능을 테스트한다.")
    void validateMenu() {
        /**
         * given : 레드와인과 해산물파스타를 주문하고, 초기 주문 금액은 0원이다.
         * when : 레드와인 2잔, 해산물파스타 1개를 주문한다.
         * then : 총 주문 금액은 (60000원 * 2) + (30000원 * 1)으로 155000원이다.
         */
        SelectionMenu selectionMenu = new SelectionMenu();
        Menu wine = RED_WINE;
        Menu pasta = SEAFOOD_PASTA;
        int orderAmount = 0;

        selectionMenu.saveMenu(wine, 2);
        selectionMenu.saveMenu(pasta, 1);
        Map<Menu, Integer> menus = selectionMenu.getSelectionMenu();
        for (Map.Entry<Menu, Integer> entry : menus.entrySet()) {
            Menu menu = entry.getKey();
            int quantity = entry.getValue();
            orderAmount += menu.getPrice() * quantity;
        }

        assertThat(orderAmount).isEqualTo(155_000);
    }

    @Test
    @DisplayName("크리스마스 할인이 적용되는지 확인한다.")
    void getChristmasDiscount() {
        /**
         * given : 방문 날짜로 5일이 주어진다.
         * when : 크리스마스 할인 정책을 적용한다.
         * then : 적용될 할인 금액은 1400원이다.
         */
        VisitDate visitDate = new VisitDate("5");
        SelectionMenu selectionMenu = new SelectionMenu();
        Order order = new Order(visitDate, selectionMenu);

        int discount = order.getChristmasDiscount();
        int expectedDiscount = 1_400;

        assertEquals(expectedDiscount, discount);
    }

    @Test
    @DisplayName("평일이 아니면서 디저트가 없는 경우를 테스트한다.")
    void getWeekdayDiscount_noDessert() {
        /**
         * given : 파스타와 초콜릿 케이크, 1일(금요일)이 주어진다.
         * when : 할인 정책을 적용한다.
         * then : 할인이 적용되지 않아 0원을 반환한다.
         */
        SelectionMenu selectionMenu = new SelectionMenu();
        selectionMenu.saveMenu(SEAFOOD_PASTA, 3);
        VisitDate visitDate = new VisitDate("1");
        Order order = new Order(visitDate, selectionMenu);

        int discount = order.getWeekdayDiscount();
        int expectedDiscount = 0;

        assertEquals(expectedDiscount, discount);
    }

    @Test
    @DisplayName("평일이면서 디저트가 하나 포함되어 있는 경우를 테스트한다.")
    void getWeekdayDiscount_hasDessert_caseOne() {
        /**
         * given : 파스타와 초콜릿 케이크, 5일(화요일)이 주어진다.
         * when : 할인 정책을 적용한다.
         * then : 디저트 메뉴가 두개로 4046원을 반환한다.
         */
        SelectionMenu selectionMenu = new SelectionMenu();
        selectionMenu.saveMenu(SEAFOOD_PASTA, 3);
        selectionMenu.saveMenu(CHOCOLATE_CAKE, 2);
        VisitDate visitDate = new VisitDate("5");
        Order order = new Order(visitDate, selectionMenu);

        int discount = order.getWeekdayDiscount();
        int expectedDiscount = 4046;

        assertEquals(expectedDiscount, discount);
    }

    @Test
    @DisplayName("평일이 아닌데 디저트가 포함되어 있는 경우를 테스트한다.")
    void getWeekdayDiscount_hasDessert_caseTwo() {
        /**
         * given : 파스타와 초콜릿 케이크, 23일(토요일)이 주어진다.
         * when : 할인 정책을 적용한다.
         * then : 디저트 메뉴가 포함되어있지만, 평일이기 때문에 0원을 반환한다.
         */
        SelectionMenu selectionMenu = new SelectionMenu();
        selectionMenu.saveMenu(SEAFOOD_PASTA, 3);
        selectionMenu.saveMenu(CHOCOLATE_CAKE, 2);
        VisitDate visitDate = new VisitDate("23");
        Order order = new Order(visitDate, selectionMenu);

        int discount = order.getWeekdayDiscount();
        int expectedDiscount = 0;

        assertEquals(expectedDiscount, discount);
    }

    @Test
    @DisplayName("주말이면서 메인디쉬가 포함된 경우 할인을 적용한다.")
    void getWeekendDiscount_containMainDish_weekend() {
        /**
         * given : 주문 정보에 주말(1일 금요일), 메인디쉬가 포함된다.
         * when : 할인 정책을 적용한다.
         * then : 메인디쉬는 2개로 4046원을 반환한다.
         */
        SelectionMenu selectionMenu = new SelectionMenu();
        selectionMenu.saveMenu(SEAFOOD_PASTA, 2);
        selectionMenu.saveMenu(CHOCOLATE_CAKE, 2);
        VisitDate visitDate = new VisitDate("1");
        Order order = new Order(visitDate, selectionMenu);

        int discount = order.getWeekendDiscount();
        int expectedDiscount = 4046;

        assertEquals(expectedDiscount, discount);
    }

    @Test
    @DisplayName("주말이 아니면서, 메인디쉬가 포함된 경우 할인을 적용하지 않는다.")
    void getWeekendDiscount_containMainDish_weekDay() {
        /**
         * given : 주문 정보에 평일(4일 월요일), 메인디쉬가 포함된다.
         * when : 할인 정책을 적용한다.
         * then : 메인디쉬가 포함되어있어도 평일이기 때문에 적용하지 않는다.
         */
        SelectionMenu selectionMenu = new SelectionMenu();
        selectionMenu.saveMenu(SEAFOOD_PASTA, 2);
        selectionMenu.saveMenu(CHOCOLATE_CAKE, 2);
        VisitDate visitDate = new VisitDate("4");
        Order order = new Order(visitDate, selectionMenu);

        int discount = order.getWeekendDiscount();
        int expectedDiscount = 0;

        assertEquals(expectedDiscount, discount);
    }

    @Test
    @DisplayName("주말이지만 메인디쉬가 포함되어 있지 않은 경우 할인을 적용하지 않는다.")
    void getWeekendDiscount_notContainMainDish_weekend() {
        /**
         * given : 주문 정보에 주말(9일 토요일), 메인디쉬가 포함되지 않는다.
         * when : 할인 정책을 적용한다.
         * then : 메인디쉬가 없어 할인을 적용하지 않는다.
         */
        SelectionMenu selectionMenu = new SelectionMenu();
        selectionMenu.saveMenu(CHAMPAGNE, 2);
        selectionMenu.saveMenu(CHOCOLATE_CAKE, 2);
        VisitDate visitDate = new VisitDate("9");
        Order order = new Order(visitDate, selectionMenu);

        int discount = order.getWeekendDiscount();
        int expectedDiscount = 0;

        assertEquals(expectedDiscount, discount);
    }

    @Test
    @DisplayName("특별 할인 정책의 적용 여부를 테스트한다.")
    void getSpecialDiscount_() {
        /**
         * given : 주문 정보와 방문 날짜(3일)가 주어진다.
         * when : 할인 정책을 적용한다.
         * then : 3일은 특별 할인 정책이 적용되어 1000원을 반환한다.
         */
        SelectionMenu selectionMenu = new SelectionMenu();
        selectionMenu.saveMenu(SEAFOOD_PASTA, 2);
        VisitDate visitDate = new VisitDate("3");
        Order order = new Order(visitDate, selectionMenu);

        int discount = order.getSpecialDiscount();
        int expectedDiscount = 1000;

        assertEquals(expectedDiscount, discount);
    }

    @Test
    @DisplayName("특별 할인 정책의 적용 여부를 테스트한다.")
    void getSpecialDiscount() {
        /**
         * given : 주문 정보와 방문 날짜(5일)가 주어진다.
         * when : 할인 정책을 적용한다.
         * then : 5일은 특별할인에 포함되지 않아 0원을 반환한다.
         */
        SelectionMenu selectionMenu = new SelectionMenu();
        selectionMenu.saveMenu(SEAFOOD_PASTA, 2);
        VisitDate visitDate = new VisitDate("5");
        Order order = new Order(visitDate, selectionMenu);

        int discount = order.getSpecialDiscount();
        int expectedDiscount = 0;

        assertEquals(expectedDiscount, discount);
    }
}