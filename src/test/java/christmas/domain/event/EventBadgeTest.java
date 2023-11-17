package christmas.domain.event;

import static org.assertj.core.api.Assertions.assertThat;

import static christmas.domain.event.EventBadge.NO_BADGE;
import static christmas.domain.event.EventBadge.SANTA;
import static christmas.domain.event.EventBadge.STAR;
import static christmas.domain.event.EventBadge.TREE;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class EventBadgeTest {

    @DisplayName("혜택금액에 맞는 12월 이벤트 배지를 생성한다.")
    @MethodSource
    @ParameterizedTest
    void badgeTest(int totalBenefitAmount, EventBadge expected) {
        EventBadge actual = EventBadge.from(totalBenefitAmount);

        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> badgeTest() {
        return Stream.of(
                Arguments.of(0, NO_BADGE),
                Arguments.of(2500, NO_BADGE),
                Arguments.of(5000, STAR),
                Arguments.of(7500, STAR),
                Arguments.of(10000, TREE),
                Arguments.of(15000, TREE),
                Arguments.of(20000, SANTA),
                Arguments.of(25000, SANTA)
        );
    }
}
