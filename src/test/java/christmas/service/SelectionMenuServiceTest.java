package christmas.service;

import christmas.dao.MenuRepository;
import christmas.domain.SelectionMenu;
import christmas.domain.menu.Beverage;

import christmas.domain.menu.Menu;
import christmas.util.StringConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static christmas.domain.menu.Beverage.CHAMPAGNE;
import static christmas.domain.menu.Beverage.RED_WINE;
import static org.assertj.core.api.Assertions.*;

class SelectionMenuServiceTest {

    private MenuService menuService;
    private MenuRepository menuRepository;
    private SelectionMenuService selectionMenuService;


    @BeforeEach
    public void setup() {
        menuRepository = MenuRepository.getInstance();
        menuService = new MenuService(menuRepository);
        selectionMenuService = new SelectionMenuService();

        // 메뉴에 샴페인과 레드와인을 추가한다.
        menuRepository.saveMenu(Beverage.CHAMPAGNE);
        menuRepository.saveMenu(RED_WINE);
    }

    @Test
    @DisplayName("메뉴를 선택하고 데이터가 담겨있는지 확인한다.")
    public void saveSelectionMenu_successful() {
        /**
         * given : 입력값으로 샴페인-1,레드와인-1이 주어진다.
         * when : 입력값을 저장한다.
         * then : 예상한 메뉴가 저장되어 있는지 확인한다.
         */
        String input = "샴페인-1,레드와인-1";
        HashMap<String, Integer> inputMap = StringConverter.convertToMap(input);

        SelectionMenu menu = selectionMenuService.saveSelectionMenu(inputMap);
        Map<Menu, Integer> selectionMenu = menu.getSelectionMenu();


        assertThat(selectionMenu).containsEntry(CHAMPAGNE, 1)
                                .containsEntry(RED_WINE, 1);
    }
}