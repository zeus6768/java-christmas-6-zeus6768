package christmas.view;

import static christmas.view.InputConstant.BLANK;
import static christmas.view.InputConstant.EMPTY;
import static christmas.view.InputMessage.ASK_VISIT_DATE;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.VisitDate;

public class InputView {

    public InputView() {
    }

    public VisitDate askVisitDate() {
        String input = input(ASK_VISIT_DATE);
        int visitDate = Integer.parseInt(input);
        return VisitDate.from(visitDate);
    }

    private String input(final String message) {
        System.out.println(message);
        return trim(Console.readLine());
    }

    private String trim(final String message) {
        return message.replace(BLANK, EMPTY);
    }
}
