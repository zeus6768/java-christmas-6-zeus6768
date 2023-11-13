package christmas.view.input.exception;

import java.time.DateTimeException;
import java.util.NoSuchElementException;
import java.util.function.Supplier;

public class InputExceptionHandler {

    private static final String ERROR = "[ERROR] ";

    public <T> T handle(Supplier<T> callback, final String message) {
        while (true) {
            try {
                return callback.get();
            } catch (IllegalArgumentException
                     | NoSuchElementException
                     | ArrayIndexOutOfBoundsException
                     | DateTimeException exception) {
                System.out.println(ERROR + message);
            }
        }
    }
}
