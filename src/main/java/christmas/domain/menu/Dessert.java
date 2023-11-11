package christmas.domain.menu;

public enum Dessert implements Menu {

    CHOCOLATE_CAKE("초코케이크", 15_000),
    ICE_CREAM("아이스크림", 5_000);

    private String name;
    private int price;

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    Dessert(String name, int price) {
        this.name = name;
        this.price = price;
    }
}
