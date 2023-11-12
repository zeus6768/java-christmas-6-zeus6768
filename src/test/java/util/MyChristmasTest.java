package util;

import java.io.ByteArrayInputStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.menu.Menus;

public class MyChristmasTest {

    Menus menus = new Menus();

    protected void setInput(String... args) {
        final byte[] buf = String.join("\n", args).getBytes();
        System.setIn(new ByteArrayInputStream(buf));
    }

    @AfterEach
    void closeInput() {
        Console.close();
    }

    @Test
    void myTest() {

    }
}
