package christmas.view;

import static org.assertj.core.api.Assertions.assertThat;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static christmas.view.input.exception.InputExceptionMessage.INVALID_DATE;
import static christmas.view.input.exception.InputExceptionMessage.INVALID_ORDER;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import camp.nextstep.edu.missionutils.test.NsTest;
import christmas.domain.eventplanner.VisitDate;
import christmas.domain.menu.Menus;
import christmas.view.input.InputView;
import christmas.view.input.exception.InputExceptionHandler;
import util.MyChristmasTest;

class InputViewTest extends MyChristmasTest {

    InputView inputView = new InputView(new Menus(), new InputExceptionHandler());

    @DisplayName("방문 날짜 입력")
    @Nested
    class visitDateInputTest extends NsTest {

        private final String EXIT_INPUT = "1";

        @DisplayName("정상적인 방문 날짜를 입력한다")
        @MethodSource
        @ParameterizedTest
        void inputVisitDate(String input, int expected) {
            // given
            setInput(input);

            // when
            VisitDate visitDate = inputView.askVisitDate();

            // then
            assertThat(visitDate.getDay())
                    .isEqualTo(expected);
        }

        private static Stream<Arguments> inputVisitDate() {
            return IntStream.range(1, 31)
                    .mapToObj(number ->
                            Arguments.of(String.valueOf(number), number)
                    );
        }

        @DisplayName("숫자가 아닌 값을 입력하면 오류 메시지를 출력하고 재입력한다.")
        @Test
        void inputVisitDateByNonDigit() {
            assertSimpleTest(() -> {
                run("1000j", EXIT_INPUT);
                assertThat(output())
                        .contains(INVALID_DATE);
            });
        }

        @DisplayName("1~31 범위를 벗어난 숫자를 입력하면 오류 메시지를 출력하고 재입력한다.")
        @Test
        void inputVisitDateOutOfRange() {
            assertSimpleTest(() -> {
                run("0", "32", EXIT_INPUT);
                assertThat(output())
                        .contains(INVALID_DATE);
            });
        }

        @Override
        protected void runMain() {
            inputView.askVisitDate();
        }
    }

    @DisplayName("메뉴와 개수 입력")
    @Nested
    class orderInputTest extends NsTest {

        private final String EXIT_INPUT = "제로콜라-1";

        @DisplayName("정상적인 메뉴와 개수를 입력한다.")
        @MethodSource
        @ParameterizedTest
        void inputOrder(String input, int sum) {
            assertSimpleTest(() -> run(input));
        }

        private static Stream<Arguments> inputOrder() {
            return Stream.of(
                    Arguments.of("타파스-2", 2),
                    Arguments.of("타파스-1,제로콜라-1", 2),
                    Arguments.of("양송이스프-1,타파스-2,시저샐러드-3", 6),
                    Arguments.of("티본스테이크-4,바비큐립-3,해산물파스타-2,크리스마스파스타-1", 10),
                    Arguments.of("초코케이크-2,아이스크림-2", 4),
                    Arguments.of("제로콜라-1,레드와인-1,샴페인-1", 3)
            );
        }

        @DisplayName("존재하지 않는 메뉴를 입력하면 오류 메시지를 출력하고 재입력한다.")
        @Test
        void inputOrderByMenuNotExist() {
            assertSimpleTest(() -> {
                run("피자-1",
                        "스파게티-2",
                        "샐러드-3",
                        "샌드위치-4",
                        "초밥-5",
                        "카레-6",
                        "타코-7",
                        "스테이크-8",
                        "떡볶이-9",
                        EXIT_INPUT
                );
                assertThat(output())
                        .contains(INVALID_ORDER);
            });
        }

        @DisplayName("입력 양식과 다르게 입력하면 오류 메시지를 출력하고 재입력한다.")
        @Test
        void inputOrderByInvalidFormat() {
            assertSimpleTest(() -> {
                run("타파스-1-제로콜라-1",
                        "양송이스프-시저샐러드-1",
                        "티본스테이크,바비큐립-1",
                        "초코케이크-1,샴페인--3",
                        "제로콜라,레드와인,샴페인",
                        "샴페인-,-,-,제로콜라,,-",
                        EXIT_INPUT
                );
                assertThat(output())
                        .contains(INVALID_ORDER);
            });
        }

        @DisplayName("메뉴 개수의 합이 20개를 초과하면 오류 메시지를 출력하고 재입력한다.")
        @Test
        void inputOrderByExceededMenuCount() {
            assertSimpleTest(() -> {
                run("타파스-10,제로콜라-10,양송이스프-1,시저샐러드-1",
                        EXIT_INPUT
                );
                assertThat(output())
                        .contains(INVALID_ORDER);
            });
        }

        @Override
        protected void runMain() {
            inputView.askOrder();
        }
    }
}
