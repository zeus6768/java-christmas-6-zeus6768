package christmas.view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import christmas.domain.VisitDate;
import util.MyTest;

class InputViewTest extends MyTest {

    InputView inputView = new InputView(new InputValidator());

    @DisplayName("방문 날짜 입력")
    @Nested
    class visitDateInputTest {

        @DisplayName("정상적인 방문 날짜를 입력한다")
        @MethodSource
        @ParameterizedTest
        void inputVisitDate(String input, int expected) {
            // given
            setInput(input);

            // when
            VisitDate visitDate = inputView.askVisitDate();

            // then
            assertThat(visitDate.getDate()).isEqualTo(expected);
        }

        @DisplayName("숫자가 아닌 값을 입력하면 예외를 발생시킨다.")
        @ValueSource(strings = {"a", "^", "100j", "\n"})
        @ParameterizedTest
        void inputVisitDateByNonDigit(String input) {
            // given
            setInput(input);

            // when & then
            assertThatThrownBy(() -> inputView.askVisitDate())
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @DisplayName("1~31 범위를 벗어난 숫자를 입력하면 예외를 발생시킨다.")
        @MethodSource
        @ParameterizedTest
        void inputVisitDateOutOfRange(String input) {
            // given
            setInput(input);

            // when & then
            assertThatThrownBy(() -> inputView.askVisitDate())
                    .isInstanceOf(IllegalArgumentException.class);
        }

        private static Stream<Arguments> inputVisitDate() {
            return IntStream.range(1, 32)
                    .mapToObj(number -> Arguments.of(String.valueOf(number), number));
        }

        private static Stream<String> inputVisitDateOutOfRange() {
            int value = 1;
            while (1 <= value && value <= 31) {
                value = random.nextInt();
            }
            return Stream.of(String.valueOf(value));
        }
    }


}
