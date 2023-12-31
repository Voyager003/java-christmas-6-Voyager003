package christmas.domain;

import christmas.dao.MenuRepository;
import christmas.domain.menu.Menu;
import christmas.domain.order.SelectionMenu;
import christmas.service.MenuService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static christmas.domain.menu.Beverage.CHAMPAGNE;
import static christmas.domain.menu.Beverage.RED_WINE;
import static christmas.domain.menu.MainDish.SEAFOOD_PASTA;
import static org.assertj.core.api.Assertions.*;


class SelectionMenuTest {

    private MenuService menuService;
    private MenuRepository menuRepository;

    @BeforeEach
    public void setup() {
        menuRepository = MenuRepository.getInstance();
        menuService = new MenuService(menuRepository);

        // 메뉴에 샴페인과 레드와인을 추가한다.
        menuRepository.saveMenu(CHAMPAGNE);
        menuRepository.saveMenu(RED_WINE);
        menuRepository.saveMenu(SEAFOOD_PASTA);
    }

    @Test
    @DisplayName("입력한 메뉴가 선택 메뉴에 저장되는지 확인한다.")
    void generateSelectionMenu_expectMenu() {
        /**
         * given : 해산물파스타 1개와 레드와인 1개를 주문한다.
         * when : 입력한 메뉴를 선택 메뉴에 저장한다.
         * then : 선택 메뉴에는 샴페인 1개, 레드와인 1개가 저장되어 있다.
         */
        String input = "해산물파스타-1,레드와인-1";

        SelectionMenu menu = new SelectionMenu(input);
        Map<Menu, Integer> selectionMenu = menu.getSelectionMenu();

        assertThat(selectionMenu).containsEntry(SEAFOOD_PASTA, 1)
                .containsEntry(RED_WINE, 1);
    }

    @Test
    @DisplayName("조건에 맞는 입력을 받는다면, 예외를 발생시키지 않는다.")
    void validateSelectionMenu_noException() {
        /**
         * given : 해산물파스타 2개와 레드와인 1개를 주문한다.
         * when : 입력한 메뉴를 선택 메뉴에 저장한다.
         * then : 입력 조건을 충족하여 예외를 발생시키지 않는다.
         */
        String input = "해산물파스타-2,레드와인-1";

        assertThatCode(() -> new SelectionMenu(input))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("메뉴에 없는 메뉴를 입력하면 예외가 발생한다.")
    void validateMenuName_exception() {
        /**
         * given : 샴페인과 레드와인을 입력한다.
         * when : 입력한 메뉴를 선택 메뉴에 저장한다.
         * then : 없는 메뉴(탕후루)를 입력하여 예외가 발생한다.
         */
        String input = "탕후루-1,레드와인-1";

        assertThatThrownBy(() -> new SelectionMenu(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @Test
    @DisplayName("주문 수량이 0개 이하이면 예외가 발생한다.")
    void validateQuantity_exception() {
        /**
         * given : 샴페인과 레드와인을 0개 주문한다.
         * when : 입력한 메뉴를 선택 메뉴에 저장한다.
         * then : 주문 수량이 0개 이하이므로 예외가 발생한다.
         */
        String input = "샴페인-0,레드와인-0";

        assertThatThrownBy(() -> new SelectionMenu(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @Test
    @DisplayName("중복된 메뉴 입력 시, 예외가 발생한다.")
    void validateDuplicateMenu_exception() {
        /**
         * given : 샴페인 1개를 두 번 주문한다.
         * when : 입력한 메뉴를 선택 메뉴에 저장한다.
         * then : 중복된 메뉴 입력으로 예외가 발생한다.
         */
        String input = "샴페인-1,샴페인-1";

        assertThatThrownBy(() -> new SelectionMenu(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @Test
    @DisplayName("메뉴의 갯수가 20개를 초과한다면, 예외가 발생한다.")
    void validateMaximumMenu_exception() {
        /**
         * given : 해산물파스타 22개를 주문한다.
         * when : 입력한 메뉴를 선택 메뉴에 저장한다.
         * then : 중복된 메뉴 입력으로 예외가 발생한다.
         */
        String input = "샴페인-3,해산물파스타-20";

        assertThatThrownBy(() -> new SelectionMenu(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 메뉴의 총 주문 갯수가 20개를 초과했습니다. 다시 입력해 주세요.");
    }

    @Test
    @DisplayName("주문 메뉴가 모두 음료라면. 예외가 발생한다")
    void validateOnlyContainBeverage_exception() {
        /**
         * given : Beverage(샴페인, 레드와인) 3개를 주문한다.
         * when : 입력한 메뉴를 선택 메뉴에 저장한다.
         * then : 선택 메뉴는 모두 음료이므로 예외가 발생한다.
         */
        String input = "샴페인-3,레드와인-3";

        assertThatThrownBy(() -> new SelectionMenu(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining( "[ERROR] 음료만 주문할 수 없습니다. 다시 입력해 주세요.");
    }
}