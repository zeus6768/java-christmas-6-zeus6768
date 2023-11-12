package christmas.domain.menu;

public interface Menu {
    Menu NOT_EXISTS = new Menu() {
        @Override
        public String getKoreanName() {
            return "";
        }

        @Override
        public int getPrice() {
            return 0;
        }
    };

    String getKoreanName();
    int getPrice();
}
