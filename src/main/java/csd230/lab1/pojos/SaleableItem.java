package csd230.lab1.pojos;

public interface SaleableItem {
    void sellItem();
    double getPrice();

    int getQuantity();

    String getDescription();

    boolean sell(int quantity);
}
