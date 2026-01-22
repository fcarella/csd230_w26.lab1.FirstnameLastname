package csd230.lab1.pojos;

public abstract class AbstractCosmetics extends Product {
    private String skinType;

    public AbstractCosmetics() {
        super();
    }

    public AbstractCosmetics(String productId, String skinType) {
        this.setProductId(productId);
        this.skinType = skinType;
    }

    public String getSkinType() {
        return skinType;
    }

    public void setSkinType(String skinType) {
        this.skinType = skinType;
    }

    @Override
    public void sellItem() {
        System.out.println("Selling cosmetic product ID: " + getProductId() +
                " for skin type: " + skinType);
    }

    @Override
    public double getPrice() {
        // Since Product is abstract and doesn't have price,
        // concrete cosmetics classes must implement this
        return 0.0; // Default implementation - override in subclasses
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{productId='" + getProductId() +
                "', skinType='" + skinType + "'}";
    }
}