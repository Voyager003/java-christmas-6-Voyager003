package christmas.service;

import christmas.dao.MenuRepository;
import christmas.domain.menu.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static christmas.domain.menu.Appetizer.MUSHROOM_SOUP;
import static christmas.domain.menu.MainDish.TBONE_STEAK;
import static org.assertj.core.api.Assertions.assertThat;

class MenuServiceTest {

    MenuService menuService;
    MenuRepository menuRepository;

    @BeforeEach
    void setUp() {
        menuRepository = MenuRepository.getInstance();
        menuRepository.getMenu().clear();
        menuService = new MenuService(menuRepository);
    }

    @Test
    @DisplayName("메뉴를 저장하는 기능을 테스트한다.")
    void saveMenu_validate() {
        /**
         * given : 메뉴(양송이수프)가 주어진다.
         * when : 메뉴를 저장한다.
         * then : 저장된 메뉴의 값을 확인한다.
         */
        Menu menu = MUSHROOM_SOUP;

        menuService.saveMenu(menu);

        String name = MUSHROOM_SOUP.getName();
        int price = MUSHROOM_SOUP.getPrice();
        assertThat(name).isEqualTo("양송이수프");
        assertThat(price).isEqualTo(6000);
    }

    @Test
    @DisplayName("메뉴를 조회하는 기능을 테스트한다.")
    void findByName_findMenuByName() {
        /**
         * given : 메뉴(티본스테이크)가 주어진다.
         * when : 메뉴를 저장하고, 저장된 메뉴를 조회한다.
         * then : 저장된 메뉴의 값을 확인한다.
         */
        Menu menu = TBONE_STEAK;

        menuService.saveMenu(menu);
        Menu findMenu = menuService.findByName("티본스테이크");

        assertThat(findMenu).isEqualTo(TBONE_STEAK);
    }
}