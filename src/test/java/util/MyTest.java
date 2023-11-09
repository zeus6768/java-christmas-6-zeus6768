package util;

import java.io.ByteArrayInputStream;
import java.util.Random;

import org.junit.jupiter.api.AfterEach;

import camp.nextstep.edu.missionutils.Console;

public class MyTest {

    protected static Random random = new Random();

    @AfterEach
    void cleanUp() {
        Console.close();
    }

    public void setInput(String... args) {
        final byte[] buf = String.join("\n", args).getBytes();
        System.setIn(new ByteArrayInputStream(buf));
    }
}
