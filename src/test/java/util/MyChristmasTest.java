package util;

import java.io.ByteArrayInputStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.menu.Menus;

public class MyChristmasTest {

    protected void setInput(String... args) {
        final byte[] buf = String.join("\n", args).getBytes();
        System.setIn(new ByteArrayInputStream(buf));
    }

    @BeforeEach
    void setUp() {
        Menus.initialize();
    }

    @AfterEach
    void closeInput() {
        Console.close();
    }
}