package csd230.lab1.pojos;

import csd230.lab1.entities.CarEntity;

/**
 * DTO for {@link CarEntity}
 */
public class Car extends Vehicle {
    private int numberOfDoors;

    public Car() {
        super();
    }

    public Car(String productId, String title, double price, int quantity,
               String engineType, int numberOfDoors) {
        super(productId, title, price, quantity, engineType);
        this.numberOfDoors = numberOfDoors;
    }

    // Getters and Setters
    public int getNumberOfDoors() { return numberOfDoors; }
    public void setNumberOfDoors(int numberOfDoors) { this.numberOfDoors = numberOfDoors; }

    @Override
    public String toString() {
        return "Car{productId='" + getProductId() +
                "', title='" + getTitle() +
                "', engine='" + getEngineType() +
                "', doors=" + numberOfDoors +
                ", price=$" + getPrice() +
                ", quantity=" + getQuantity() + "}";
    }

    @Override
    public void sellItem() {

    }
}