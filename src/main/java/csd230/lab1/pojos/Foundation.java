package csd230.lab1.pojos;

public class Foundation extends AbstractCosmetics {
    private String coverageLevel;
    private double price;

    public Foundation() {
        super();
    }

    public Foundation(String productId, String skinType, String coverageLevel, double price) {
        super(productId, skinType);
        this.coverageLevel = coverageLevel;
        this.price = price;
    }

    public String getCoverageLevel() {
        return coverageLevel;
    }

    public void setCoverageLevel(String coverageLevel) {
        this.coverageLevel = coverageLevel;
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
        System.out.println("Selling Foundation ID: " + getProductId() +
                " with " + coverageLevel + " coverage" +
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
        return super.toString() + ", coverageLevel='" + coverageLevel +
                "', price=" + price + "}";
    }
}