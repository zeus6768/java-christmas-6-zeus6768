package christmas.view.input;

import static java.util.stream.Collectors.toMap;

import static christmas.view.input.InputConstant.COUNT_INDEX;
import static christmas.view.input.InputConstant.DELIMITER_COMMA;
import static christmas.view.input.InputConstant.DELIMITER_HYPHEN;
import static christmas.view.input.InputConstant.MENU_AND_COUNT_SPLIT_LENGTH;
import static christmas.view.input.InputConstant.MENU_INDEX;
import static christmas.view.input.InputMessage.ASK_ORDER;
import static christmas.view.input.InputMessage.ASK_VISIT_DATE;
import static christmas.view.input.exception.InputExceptionMessage.INVALID_DATE;
import static christmas.view.input.exception.InputExceptionMessage.INVALID_ORDER;

import java.util.Map;
import java.util.stream.Stream;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.eventplanner.Order;
import christmas.domain.eventplanner.VisitDate;
import christmas.domain.menu.Menu;
import christmas.domain.menu.Menus;
import christmas.view.input.exception.InputExceptionHandler;

public class InputView {

    private final Menus menus;
    private final InputExceptionHandler inputHandler;

    public InputView(Menus menus, InputExceptionHandler inputHandler) {
        this.menus = menus;
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
                        menuAndCount -> menus.find(menuAndCount[MENU_INDEX]),
                        menuAndCount -> Integer.parseInt(menuAndCount[COUNT_INDEX])
                ));
        return Order.from(order);
    }

    private String input(final String message) {
        System.out.println(message);
        return Console.readLine();
    }
}
