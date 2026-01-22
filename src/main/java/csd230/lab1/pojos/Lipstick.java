package csd230.lab1.pojos;

public class Lipstick extends AbstractCosmetics {
    private String finishType;
    private double price;

    public Lipstick() {
        super();
    }

    public Lipstick(String productId, String skinType, String finishType, double price) {
        super(productId, skinType);
        this.finishType = finishType;
        this.price = price;
    }

    public String getFinishType() {
        return finishType;
    }

    public void setFinishType(String finishType) {
        this.finishType = finishType;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public void sellItem() {
        System.out.println("Selling Lipstick ID: " + getProductId() +
                " with " + finishType + " finish" +
                " for " + getSkinType() + " skin" +
                " at price: $" + price);
    }

    @Override
    public void edit() {

    }

    @Override
    public void initialize() {

    }

    @Override
    public String toString() {
        return super.toString() + ", finishType='" + finishType +
                "', price=" + price + "}";
    }
}