package christmas.domain;

import christmas.dao.MenuRepository;
import christmas.domain.menu.Menu;
import christmas.service.MenuService;
import christmas.util.StringConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static christmas.domain.menu.Beverage.CHAMPAGNE;
import static christmas.domain.menu.Beverage.RED_WINE;
import static org.assertj.core.api.Assertions.assertThat;


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
    }

    @Test
    @DisplayName("입력한 메뉴가 선택 메뉴에 저장되는지 확인한다.")
    void generateSelectionMenu_expectMenu() {
        /**
         * given : 샴페인과 레드와인을 입력한다.
         * when : 입력한 메뉴를 선택 메뉴에 저장한다.
         * then : 선택 메뉴에는 샴페인 1개, 레드와인 1개가 저장되어 있다.
         */
        String input = "샴페인-1,레드와인-1";
        HashMap<String, Integer> inputs = StringConverter.convertToMap(input);

        SelectionMenu menu = new SelectionMenu(inputs);
        Map<Menu, Integer> selectionMenu = menu.getSelectionMenu();

        assertThat(selectionMenu).containsEntry(CHAMPAGNE, 1)
                .containsEntry(RED_WINE, 1);
    }
}