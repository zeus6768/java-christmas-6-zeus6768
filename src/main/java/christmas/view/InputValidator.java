package christmas.view;

public class InputValidator {

    public int validateInteger(final String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException();
        }
    }
}
