package christmas.config;


import christmas.dao.MenuRepository;
import christmas.service.MenuService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;

class PlannerConfigTest {

    @Test
    @DisplayName("메뉴 초기화 기능 테스트")
    void initializeMenu_validateMenuSize() {
        /**
         * given : PlannerConfig 객체 생성
         * when : initializeMenu() 호출한다.
         * then : 모든 메뉴의 개수는 12개로, 예상되는 메뉴의 개수는 12개이다.
         */
        MenuService menuService = new MenuService(MenuRepository.getInstance());
        PlannerConfig plannerConfig = new PlannerConfig(menuService);

        menuService.getMenuSize();

        assertThat(menuService.getMenuSize()).isEqualTo(12);
    }
}
