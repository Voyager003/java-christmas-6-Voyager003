package christmas.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public static String inputVisitDate() {
        OutputView.printMessageInputDate();
        return Console.readLine();
    }

    public static String inputMenu() {
        OutputView.printMessageInputMenu();
        return Console.readLine();
    }
}
