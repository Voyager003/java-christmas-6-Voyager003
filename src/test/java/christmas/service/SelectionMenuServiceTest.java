package christmas.service;

import christmas.dao.MenuRepository;
import christmas.domain.SelectionMenu;
import christmas.domain.menu.Beverage;

import christmas.util.StringConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.*;

class SelectionMenuServiceTest {

    private MenuService menuService;
    private MenuRepository menuRepository;
    private SelectionMenuService selectionMenuService;
    private SelectionMenu selectionMenu;


    @BeforeEach
    public void setup() {
        menuRepository = MenuRepository.getInstance();
        menuService = new MenuService(menuRepository);
        selectionMenuService = new SelectionMenuService();
        selectionMenu = new SelectionMenu();

        // 메뉴에 샴페인과 레드와인을 추가한다.
        menuRepository.saveMenu(Beverage.CHAMPAGNE);
        menuRepository.saveMenu(Beverage.RED_WINE);
    }

    @Test
    @DisplayName("조건에 맞는 입력이 주어진다면 예외를 던지지 않는다.")
    public void saveSelectionMenu_successful() {
        /**
         * given : 입력값으로 샴페인-1,레드와인-1이 주어진다.
         * when : 입력값을 저장한다.
         * then : 올바른 입력 조건으로 예외를 던지지 않는다.
         */
        String input = "샴페인-1,레드와인-1";
        HashMap<String, Integer> inputMap = StringConverter.convertToMap(input);

        assertThatCode(() -> selectionMenuService.saveSelectionMenu(inputMap))
                .doesNotThrowAnyException();
    }


    @Test
    @DisplayName("존재하지 않는 메뉴가 입력된다면, 예외를 던진다.")
    public void saveSelectionMenu_withNonexistentMenu() {
        /**
         * given : 메뉴에 탕후루와 오이무침을 저장한다.
         * when : 탕후루-1,오이무침-1을 입력한다.
         * then : 존재하지 않는 메뉴이기 때문에 예외를 발생시킨다.
         */
        String userInput = "탕후루-1,오이무침-1";
        HashMap<String, Integer> input = StringConverter.convertToMap(userInput);

        assertThatThrownBy(() -> selectionMenuService.saveSelectionMenu(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 존재하지 않는 메뉴입니다. 다시 입력해 주세요.");
    }

    @Test
    @DisplayName("주문 수량이 1 미만이라면, 예외를 던진다.")
    public void saveSelectionMenu_withInvalidQuantity() {
        /**
         * given : 입력값으로 샴페인-0,레드와인-0이 주어진다.
         * when : 입력값을 저장한다.
         * then : 주문 수량이 1 미만으로 예외를 발생시킨다.
         */
        String userInput = "샴페인-0,레드와인-0";
        HashMap<String, Integer> input = StringConverter.convertToMap(userInput);

        assertThatThrownBy(() -> selectionMenuService.saveSelectionMenu(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 주문 수량을 1 이상의 수로 다시 입력해 주세요.");
    }
//
//    @Test
//    @DisplayName("중복된 메뉴가 입력된다면, 예외를 던진다.")
//    public void testSaveSelectionMenu_withDuplicateMenu() {
//        /**
//         * given : 입력값으로 샴페인-1,샴페인-1이 주어진다.
//         * when : 입력값을 저장한다.
//         * then : 중복된 메뉴가 입력되었기 때문에 예외를 발생시킨다.
//         */
//        String userInput = "샴페인-1,샴페인-1";
//        HashMap<String, Integer> input = StringConverter.convertToMap(userInput);
//
//        assertThatThrownBy(() -> selectionMenuService.saveSelectionMenu(input))
//                .isInstanceOf(IllegalArgumentException.class)
//                .hasMessageContaining("[ERROR] 중복된 메뉴는 입력될 수 없습니다. 다시 입력해 주세요.");
//    }
}