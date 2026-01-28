package csd230.lab1.pojos;

import csd230.lab1.entities.MotorcycleEntity;

/**
 * DTO for {@link MotorcycleEntity}
 */
public class Motorcycle extends Vehicle {
    private boolean hasSidecar;

    public Motorcycle() {
        super();
    }

    public Motorcycle(String productId, String title, double price, int quantity,
                      String engineType, boolean hasSidecar) {
        super(productId, title, price, quantity, engineType);
        this.hasSidecar = hasSidecar;
    }

    // Getters and Setters
    public boolean isHasSidecar() { return hasSidecar; }
    public void setHasSidecar(boolean hasSidecar) { this.hasSidecar = hasSidecar; }

    @Override
    public String toString() {
        return "Motorcycle{productId='" + getProductId() +
                "', title='" + getTitle() +
                "', engine='" + getEngineType() +
                "', sidecar=" + (hasSidecar ? "yes" : "no") +
                ", price=$" + getPrice() +
                ", quantity=" + getQuantity() + "}";
    }

    @Override
    public void sellItem() {

    }
}