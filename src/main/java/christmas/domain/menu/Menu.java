package christmas.domain.menu;

public interface Menu {
    Menu NO_MENU = new Menu() {
        @Override
        public String getName() {
            return "";
        }

        @Override
        public int getPrice() {
            return 0;
        }
    };

    String getName();
    int getPrice();
}
