package christmas.view;

import static christmas.view.InputMessage.ASK_VISIT_DATE;
import static christmas.view.InputMessage.INTRO;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.VisitDate;

public class InputView {

    private final InputValidator validator;

    public InputView(InputValidator validator) {
        this.validator = validator;
    }

    private void printIntro() {
        System.out.println(INTRO);
    }

    public VisitDate askVisitDate() {
        String input = input(ASK_VISIT_DATE);
        int visitDate = validator.validateInteger(input);
        return VisitDate.from(visitDate);
    }

    private String input(final String message) {
        System.out.println(message);
        return Console.readLine();
    }
}
