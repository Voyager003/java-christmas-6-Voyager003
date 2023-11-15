package christmas.domain.benefit;

import java.text.NumberFormat;

public class BenefitDetail {
    private String name;
    private int amount;

    public BenefitDetail(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public boolean isNonZero() {
        return amount > 0;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }


    @Override
    public String toString() {
        return name + ": -" + NumberFormat.getInstance().format(amount) + "원";
    }
}
