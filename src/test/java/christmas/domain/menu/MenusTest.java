package christmas.domain.menu;

import static org.assertj.core.api.Assertions.assertThat;

import static christmas.domain.menu.Appetizer.CAESAR_SALAD;
import static christmas.domain.menu.Appetizer.TAPAS;
import static christmas.domain.menu.Appetizer.WHITE_MUSHROOM_SOUP;
import static christmas.domain.menu.Dessert.CHOCOLATE_CAKE;
import static christmas.domain.menu.Dessert.ICE_CREAM;
import static christmas.domain.menu.Drink.CHAMPAGNE;
import static christmas.domain.menu.Drink.RED_WINE;
import static christmas.domain.menu.Drink.ZERO_COLA;
import static christmas.domain.menu.MainDish.BBQ_RIBS;
import static christmas.domain.menu.MainDish.CHRISTMAS_PASTA;
import static christmas.domain.menu.MainDish.SEAFOOD_PASTA;
import static christmas.domain.menu.MainDish.T_BONE_STEAK;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class MenusTest {

    Menus menus = new Menus();

    @DisplayName("한글 입력으로 메뉴를 찾는다. ")
    @MethodSource
    @ParameterizedTest
    void findMenuTest(String name, Menu expected) {
        Menu actual = menus.find(name);
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> findMenuTest() {
        return Stream.of(
                Arguments.of("양송이수프", WHITE_MUSHROOM_SOUP),
                Arguments.of("타파스", TAPAS),
                Arguments.of("시저샐러드", CAESAR_SALAD),
                Arguments.of("티본스테이크", T_BONE_STEAK),
                Arguments.of("바비큐립", BBQ_RIBS),
                Arguments.of("해산물파스타", SEAFOOD_PASTA),
                Arguments.of("크리스마스파스타", CHRISTMAS_PASTA),
                Arguments.of("초코케이크", CHOCOLATE_CAKE),
                Arguments.of("아이스크림", ICE_CREAM),
                Arguments.of("제로콜라", ZERO_COLA),
                Arguments.of("레드와인", RED_WINE),
                Arguments.of("샴페인", CHAMPAGNE)
        );
    }
}
