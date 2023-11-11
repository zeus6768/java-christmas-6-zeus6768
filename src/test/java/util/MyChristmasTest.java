package util;

import java.io.ByteArrayInputStream;
import java.util.Random;

public class MyChristmasTest {

    public final static Random RANDOM = new Random();

    public static void setInput(String... args) {
        final byte[] buf = String.join("\n", args).getBytes();
        System.setIn(new ByteArrayInputStream(buf));
    }
}
