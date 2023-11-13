package christmas.domain;

public enum Badge {
    STAR("별", 5_000),
    TREE("트리", 10_000),
    SANTA("산타", 20_000),
    NONE("없음", 0);

    private String badge;
    private int price;

    Badge(String badge, int price) {
        this.badge = badge;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return badge;
    }
}
