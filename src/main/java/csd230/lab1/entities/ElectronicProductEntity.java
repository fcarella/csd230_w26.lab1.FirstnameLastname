package csd230.lab1.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * Abstract entity representing the ElectronicProduct category in the database.
 * Inherits from ProductEntity.
 */
@Entity
@DiscriminatorValue("ELECTRONIC")
public abstract class ElectronicProductEntity extends ProductEntity {

    private String brand;
    private double price;
    private String description;

    /**
     * Default constructor required by JPA.
     */
    public ElectronicProductEntity() {
        super();
    }

    /**
     * Constructor accepting brand.
     *
     * @param brand Brand name
     */
    public ElectronicProductEntity(String brand) {
        super();
        this.brand = brand;
    }

    /**
     * Parameterized constructor.
     *
     * @param price       Product price
     * @param description Product description
     * @param brand       Brand name
     */
    public ElectronicProductEntity(double price, String description, String brand) {
        super();
        this.price = price;
        this.description = description;
        this.brand = brand;
    }

    // Getters and Setters

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ElectronicProductEntity{" +
                "brand='" + brand + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                "} " + super.toString();
    }
}