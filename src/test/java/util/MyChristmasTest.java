package util;

import java.io.ByteArrayInputStream;

import org.junit.jupiter.api.AfterEach;

import camp.nextstep.edu.missionutils.Console;

public class MyChristmasTest {

    protected void setInput(String... args) {
        final byte[] buf = String.join("\n", args).getBytes();
        System.setIn(new ByteArrayInputStream(buf));
    }

    @AfterEach
    void closeInput() {
        Console.close();
    }
}