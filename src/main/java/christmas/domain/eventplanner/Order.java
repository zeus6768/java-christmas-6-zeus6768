package christmas.domain.eventplanner;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

import christmas.domain.menu.Menu;

public class Order {

    private static final int ORDER_COUNT_MIN = 1;
    private static final int ORDER_COUNT_SUM_MAX = 20;

    private final Map<Menu, Integer> order;

    private Order(Map<Menu, Integer> order) {
        validate(order);
        this.order = new LinkedHashMap<>(order);
    }

    public static Order from(Map<Menu, Integer> order) {
        return new Order(order);
    }

    public int totalPrice() {
        return stream()
                .mapToInt(menuAndCount -> {
                            Menu menu = menuAndCount.getKey();
                            int count = menuAndCount.getValue();
                            return menu.getPrice() * count;
                        })
                .sum();
    }

    public Stream<Entry<Menu, Integer>> stream() {
        return order.entrySet().stream();
    }

    private void validate(Map<Menu, Integer> order) {
        validateOrderCountInRange(order);
        validateOrderCountSum(order);
    }

    private void validateOrderCountInRange(Map<Menu, Integer> order) {
        if (anyCountOutOfRange(order)) {
            throw new IllegalArgumentException();
        }
    }

    private void validateOrderCountSum(Map<Menu, Integer> order) {
        if (isCountSumExceeded(order)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean anyCountOutOfRange(Map<Menu, Integer> order) {
        return order.values().stream().anyMatch(this::isOutOfRange);
    }

    private boolean isOutOfRange(int count) {
        return count < ORDER_COUNT_MIN || count > ORDER_COUNT_SUM_MAX;
    }

    private boolean isCountSumExceeded(Map<Menu, Integer> order) {
        return order.values().stream().mapToInt(i -> i).sum() > ORDER_COUNT_SUM_MAX;
    }
}
