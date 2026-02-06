package csd230.lab1.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("ELECTRONICS")
public abstract class ElectronicsEntity extends ProductEntity {

    private String brand;
    private double price;

    public ElectronicsEntity() {}

    public ElectronicsEntity(String brand, double price) {
        this.brand = brand;
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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
        System.out.println("Selling electronics: " + brand + " for $" + price);
    }

    @Override
    public String toString() {
        return super.toString() + ", brand='" + brand + "', price=" + price;
    }
}
