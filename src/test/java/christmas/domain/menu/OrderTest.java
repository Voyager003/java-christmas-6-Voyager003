package christmas.domain.menu;

import christmas.dao.MenuRepository;
import christmas.domain.Order;
import christmas.domain.SelectionMenu;
import christmas.domain.date.VisitDate;
import christmas.service.MenuService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static christmas.domain.menu.Beverage.RED_WINE;
import static christmas.domain.menu.MainDish.SEAFOOD_PASTA;
import static org.assertj.core.api.Assertions.assertThat;


class OrderTest {

    private MenuService menuService;
    private MenuRepository menuRepository;

    @BeforeEach
    public void setup() {
        menuRepository = MenuRepository.getInstance();
        menuService = new MenuService(menuRepository);

        // 메뉴에 샴페인과 레드와인을 추가한다.
        menuRepository.saveMenu(SEAFOOD_PASTA);
        menuRepository.saveMenu(RED_WINE);
    }

    @Test
    @DisplayName("주문 금액을 계산하는 기능을 테스트한다.")
    void validateMenu() {
        /**
         * given : 레드와인과 해산물파스타를 주문한다.
         * when : 주문 정보를 생성한다.
         * then : 총 주문 금액은 (60000원 * 2) + (35000원 * 1)으로 155000원이다.
         */
        VisitDate visitDate = new VisitDate("1");
        SelectionMenu selectionMenu = new SelectionMenu("레드와인-2,해산물파스타-1");

        Order order = new Order(visitDate, selectionMenu);
        int orderAmount = order.getOrderAmount();

        assertThat(orderAmount).isEqualTo(155_000);
    }
}