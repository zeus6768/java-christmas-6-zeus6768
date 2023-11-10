package christmas.view;

import static java.util.stream.Collectors.toMap;

import static christmas.view.InputConstant.COUNT_INDEX;
import static christmas.view.InputConstant.DELIMITER_COMMA;
import static christmas.view.InputConstant.DELIMITER_HYPHEN;
import static christmas.view.InputConstant.MENU_AND_COUNT_SPLIT_LENGTH;
import static christmas.view.InputConstant.MENU_INDEX;
import static christmas.view.InputMessage.ASK_ORDER;
import static christmas.view.InputMessage.ASK_VISIT_DATE;
import static christmas.view.exception.InputExceptionMessage.INVALID_DATE;
import static christmas.view.exception.InputExceptionMessage.INVALID_ORDER;

import java.util.Map;
import java.util.stream.Stream;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.VisitDate;
import christmas.domain.menu.Menu;
import christmas.domain.menu.Order;
import christmas.view.exception.InputExceptionHandler;

public class InputView {

    private final InputExceptionHandler inputHandler;

    public InputView(InputExceptionHandler inputHandler) {
        this.inputHandler = inputHandler;
    }

    public VisitDate askVisitDate() {
        return inputHandler.handle(this::inputVisitDate, INVALID_DATE);
    }

    public Order askOrder() {
        return inputHandler.handle(this::inputOrder, INVALID_ORDER);
    }

    private VisitDate inputVisitDate() {
        String input = input(ASK_VISIT_DATE);
        int visitDate = Integer.parseInt(input);
        return VisitDate.from(visitDate);
    }

    private Order inputOrder() {
        String[] menuAndCounts = input(ASK_ORDER).split(DELIMITER_COMMA);
        Map<Menu, Integer> order = Stream.of(menuAndCounts)
                .map(menuAndCount -> menuAndCount.split(DELIMITER_HYPHEN, MENU_AND_COUNT_SPLIT_LENGTH))
                .collect(toMap(
                        menuAndCount -> Menu.find(menuAndCount[MENU_INDEX]),
                        menuAndCount -> Integer.parseInt(menuAndCount[COUNT_INDEX])
                ));
        return Order.from(order);
    }

    private String input(final String message) {
        System.out.println(message);
        return Console.readLine();
    }
}
