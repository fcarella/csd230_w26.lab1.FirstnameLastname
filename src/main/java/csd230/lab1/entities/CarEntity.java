package csd230.lab1.entities;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("CAR")  // For SINGLE_TABLE inheritance

public class CarEntity extends VehicleEntity {

    @Column(name = "number_of_doors")
    private int numberOfDoors;

    // Implement abstract methods from ProductEntity
    @Override
    public void sellItem() {
        if (getQuantity() > 0) {
            setQuantity(getQuantity() - 1);
        }
    }

    public CarEntity() {}

    public CarEntity(String title, String description, double price,
                     int quantity, String engineType, int numberOfDoors) {
        this.setTitle(title);
        this.setDescription(description);
        this.setPrice(price);
        this.setQuantity(quantity);
        this.setEngineType(engineType);
        this.numberOfDoors = numberOfDoors;
    }

    @Override
    public boolean sell(int quantity) {
        if (getQuantity() >= quantity) {
            setQuantity(getQuantity() - quantity);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "CarEntity{" +
                "id=" + getId() +
                ", numberOfDoors=" + numberOfDoors +
                ", engineType='" + getEngineType() + '\'' +
                ", title='" + getTitle() + '\'' +
                ", price=" + getPrice() +
                ", quantity=" + getQuantity() +
                ", description='" + getDescription() + '\'' +
                '}';
    }

    public int getNumberOfDoors() {
        return numberOfDoors;
    }

    public void setNumberOfDoors(int numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }
}