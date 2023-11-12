package christmas.domain.menu;

import christmas.domain.SelectionMenu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static christmas.domain.menu.Beverage.RED_WINE;
import static christmas.domain.menu.MainDish.SEAFOOD_PASTA;
import static org.assertj.core.api.Assertions.assertThat;


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
}