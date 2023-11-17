package christmas.domain.eventplanner;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class EventCalendarTest {

    @DisplayName("주어진 날짜가 크리스마스 디데이 이벤트 기간에 포함되는지를 결정한다.")
    @MethodSource
    @ParameterizedTest
    void isXMasDDayTest(int day, boolean expected) {
        boolean actual = EventCalendar.isXMasDDay(VisitDate.from(day));
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> isXMasDDayTest() {
        return Stream.of(
                Arguments.of(1, true), Arguments.of(2, true), Arguments.of(3, true),
                Arguments.of(4, true), Arguments.of(5, true), Arguments.of(6, true),
                Arguments.of(7, true), Arguments.of(8, true), Arguments.of(9, true),
                Arguments.of(10, true), Arguments.of(11, true), Arguments.of(12, true),
                Arguments.of(13, true), Arguments.of(14, true), Arguments.of(15, true),
                Arguments.of(16, true), Arguments.of(17, true), Arguments.of(18, true),
                Arguments.of(19, true), Arguments.of(20, true), Arguments.of(21, true),
                Arguments.of(22, true), Arguments.of(23, true), Arguments.of(24, true),
                Arguments.of(25, true), Arguments.of(26, false), Arguments.of(27, false),
                Arguments.of(28, false), Arguments.of(29, false), Arguments.of(30, false),
                Arguments.of(31, false)
        );
    }

    @DisplayName("주어진 날짜가 평일 이벤트 기간에 포함되는지를 결정한다.")
    @MethodSource
    @ParameterizedTest
    void isWeekdayTest(int day, boolean expected) {
        boolean actual = EventCalendar.isWeekday(VisitDate.from(day));
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> isWeekdayTest() {
        return Stream.of(
                Arguments.of(1, false), Arguments.of(2, false), Arguments.of(3, true),
                Arguments.of(4, true), Arguments.of(5, true), Arguments.of(6, true),
                Arguments.of(7, true), Arguments.of(8, false), Arguments.of(9, false),
                Arguments.of(10, true), Arguments.of(11, true), Arguments.of(12, true),
                Arguments.of(13, true), Arguments.of(14, true), Arguments.of(15, false),
                Arguments.of(16, false), Arguments.of(17, true), Arguments.of(18, true),
                Arguments.of(19, true), Arguments.of(20, true), Arguments.of(21, true),
                Arguments.of(22, false), Arguments.of(23, false), Arguments.of(24, true),
                Arguments.of(25, true), Arguments.of(26, true), Arguments.of(27, true),
                Arguments.of(28, true), Arguments.of(29, false), Arguments.of(30, false),
                Arguments.of(31, true)
        );
    }

    @DisplayName("주어진 날짜가 주말 이벤트 기간에 포함되는지를 결정한다.")
    @MethodSource
    @ParameterizedTest
    void isWeekendTest(int day, boolean expected) {
        boolean actual = EventCalendar.isWeekEnd(VisitDate.from(day));
        assertThat(actual).isNotEqualTo(expected);
    }

    private static Stream<Arguments> isWeekendTest() {
        return Stream.of(
                Arguments.of(1, false), Arguments.of(2, false), Arguments.of(3, true),
                Arguments.of(4, true), Arguments.of(5, true), Arguments.of(6, true),
                Arguments.of(7, true), Arguments.of(8, false), Arguments.of(9, false),
                Arguments.of(10, true), Arguments.of(11, true), Arguments.of(12, true),
                Arguments.of(13, true), Arguments.of(14, true), Arguments.of(15, false),
                Arguments.of(16, false), Arguments.of(17, true), Arguments.of(18, true),
                Arguments.of(19, true), Arguments.of(20, true), Arguments.of(21, true),
                Arguments.of(22, false), Arguments.of(23, false), Arguments.of(24, true),
                Arguments.of(25, true), Arguments.of(26, true), Arguments.of(27, true),
                Arguments.of(28, true), Arguments.of(29, false), Arguments.of(30, false),
                Arguments.of(31, true)
        );
    }

    @DisplayName("주어진 날짜가 스페셜 이벤트 기간에 포함되는지를 결정한다.")
    @MethodSource
    @ParameterizedTest
    void isSpecialDayTest(int day, boolean expected) {
        boolean actual = EventCalendar.isSpecialDay(VisitDate.from(day));
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> isSpecialDayTest() {
        return Stream.of(
                Arguments.of(1, false), Arguments.of(2, false), Arguments.of(3, true),
                Arguments.of(4, false), Arguments.of(5, false), Arguments.of(6, false),
                Arguments.of(7, false), Arguments.of(8, false), Arguments.of(9, false),
                Arguments.of(10, true), Arguments.of(11, false), Arguments.of(12, false),
                Arguments.of(13, false), Arguments.of(14, false), Arguments.of(15, false),
                Arguments.of(16, false), Arguments.of(17, true), Arguments.of(18, false),
                Arguments.of(19, false), Arguments.of(20, false), Arguments.of(21, false),
                Arguments.of(22, false), Arguments.of(23, false), Arguments.of(24, true),
                Arguments.of(25, true), Arguments.of(26, false), Arguments.of(27, false),
                Arguments.of(28, false), Arguments.of(29, false), Arguments.of(30, false),
                Arguments.of(31, true)
        );
    }
}
