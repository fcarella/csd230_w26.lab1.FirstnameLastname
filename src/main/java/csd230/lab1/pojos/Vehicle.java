package csd230.lab1.pojos;

import csd230.lab1.entities.VehicleEntity;

/**
 * DTO for {@link VehicleEntity}
 */
public abstract class Vehicle extends Product {
    private String engineType;

    public Vehicle() {
        super();
    }

    public Vehicle(String productId, String title, double price, int quantity, String engineType) {
        super(productId, title, price, quantity, "Vehicle: " + title);
        this.engineType = engineType;
    }

    // Getters and Setters
    public String getEngineType() { return engineType; }
    public void setEngineType(String engineType) { this.engineType = engineType; }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{id='" + getProductId() +
                "', title='" + getTitle() + "', engine='" + engineType +
                "', price=$" + getPrice() + "}";
    }
}