package christmas.domain;

import christmas.domain.date.VisitDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static christmas.domain.menu.Dessert.CHOCOLATE_CAKE;
import static christmas.domain.menu.MainDish.SEAFOOD_PASTA;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BenefitTest {

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
        Benefit benefit = new Benefit(order);

        int discount = benefit.getTotalBenefitAmount();
        int expectedDiscount = 1_400;

        assertEquals(expectedDiscount, discount);
    }

    @Test
    @DisplayName("주말이면서 메인디쉬가 포함되어 있는 경우를 테스트한다.")
    void getWeekendDiscount_withDessert() {
        /**
         * given : 메인디쉬와 디저트 그리고 16일(토요일)이 주어진다.
         * when : 할인 정책을 적용한다.
         * then : 적용되는 할인 정책은 크리스마스(1000), 주말(2023*2)으로 5046원이 예상된다.
         */
        SelectionMenu selectionMenu = new SelectionMenu();
        selectionMenu.saveMenu(CHOCOLATE_CAKE, 2);
        selectionMenu.saveMenu(SEAFOOD_PASTA, 2);
        VisitDate visitDate = new VisitDate("1");
        Order order = new Order(visitDate, selectionMenu);
        Benefit benefit = new Benefit(order);

        int discount = benefit.getTotalBenefitAmount();
        int expectedDiscount = 5046;

        assertEquals(expectedDiscount, discount);
    }

    @Test
    @DisplayName("주말인데 메인디쉬가 없는 경우를 테스트한다.")
    void getWeekendDiscount_hasDessert_caseOne() {
        /**
         * given : 디저트와 23일(토요일)이 주어진다.
         * when : 할인 정책을 적용한다.
         * then : 메인디쉬가 없으므로 크리스마스 할인(3200)만 적용한다.
         */
        SelectionMenu selectionMenu = new SelectionMenu();
        selectionMenu.saveMenu(CHOCOLATE_CAKE, 2);
        VisitDate visitDate = new VisitDate("23");
        Order order = new Order(visitDate, selectionMenu);
        Benefit benefit = new Benefit(order);

        int discount = benefit.getTotalBenefitAmount();
        int expectedDiscount = 3200;

        assertEquals(expectedDiscount, discount);
    }

    @Test
    @DisplayName("평일이 아닌데 디저트가 포함되어 있는 경우를 테스트한다.")
    void getWeekdayDiscount_hasDessert_caseTwo() {
        /**
         * given : 파스타와 초콜릿 케이크, 13일(평일)이 주어진다.
         * when : 할인 정책을 적용한다.
         * then : 크리스마스할인(2200)과 특별할인(1000), 평일할인(2023*2)으로 6246원을 적용
         */
        SelectionMenu selectionMenu = new SelectionMenu();
        selectionMenu.saveMenu(SEAFOOD_PASTA, 4);
        selectionMenu.saveMenu(CHOCOLATE_CAKE, 2);
        VisitDate visitDate = new VisitDate("13");
        Order order = new Order(visitDate, selectionMenu);
        Benefit benefit = new Benefit(order);

        int discount = benefit.getTotalBenefitAmount();
        int expectedDiscount = 6246;

        assertEquals(expectedDiscount, discount);
    }

    @Test
    @DisplayName("스타 뱃지를 부여하는 지 테스트한다.")
    void grantBadge_star() {
        /**
         * given : 메뉴를 주문한다.
         * when : 할인 정책을 적용한다.
         * then : 예상되는 할인액은 6246원으로 STAR 뱃지를 부여한다.
         */
        SelectionMenu selectionMenu = new SelectionMenu();
        selectionMenu.saveMenu(SEAFOOD_PASTA, 4);
        selectionMenu.saveMenu(CHOCOLATE_CAKE, 2);
        VisitDate visitDate = new VisitDate("13");
        Order order = new Order(visitDate, selectionMenu);
        Benefit benefit = new Benefit(order);

        Badge badge = benefit.getBadge();
        assertThat(badge).isEqualTo(Badge.STAR);
    }

    @Test
    @DisplayName("뱃지를 부여하지 않는 케이스를 테스트한다.")
    void grantBadge_none() {
        /**
         * given : 메뉴를 주문한다.
         * when : 할인 정책을 적용한다.
         * then : 예상되는 할인액은 1100원으로 부여되는 뱃지는 없다.
         */
        SelectionMenu selectionMenu = new SelectionMenu();
        selectionMenu.saveMenu(CHOCOLATE_CAKE, 1);
        VisitDate visitDate = new VisitDate("2");
        Order order = new Order(visitDate, selectionMenu);
        Benefit benefit = new Benefit(order);

        Badge badge = benefit.getBadge();
        assertThat(badge).isEqualTo(Badge.NONE);
    }

    @Test
    @DisplayName("산타 뱃지를 부여하는 지 테스트한다.")
    void grantBadge_santa() {
        /**
         * given : 메뉴를 주문한다.
         * when : 할인 정책을 적용한다.
         * then : 예상되는 할인액은 22430원으로 SANTA 뱃지를 부여한다.
         */
        SelectionMenu selectionMenu = new SelectionMenu();
        selectionMenu.saveMenu(SEAFOOD_PASTA, 4);
        selectionMenu.saveMenu(CHOCOLATE_CAKE, 10);
        VisitDate visitDate = new VisitDate("13");
        Order order = new Order(visitDate, selectionMenu);
        Benefit benefit = new Benefit(order);

        Badge badge = benefit.getBadge();
        assertThat(badge).isEqualTo(Badge.SANTA);
    }

    @Test
    @DisplayName("증정 메뉴 기능을 테스트한다.")
    void grantGiftEvent_getTotalBenefit() {
        /**
         * given : 메뉴를 주문한다.
         * when : 할인 정책을 적용한다.
         * then : 할인이 적용되지 않았지만, 샴페인을 증정함으로 25000원이 추가된다.
         */
        SelectionMenu selectionMenu = new SelectionMenu();
        selectionMenu.saveMenu(CHOCOLATE_CAKE, 10);
        VisitDate visitDate = new VisitDate("29");

        Order order = new Order(visitDate, selectionMenu);
        Benefit benefit = new Benefit(order);

        int benefitAmount = benefit.getTotalBenefitAmount();
        assertThat(benefitAmount).isEqualTo(25_000);
    }
}