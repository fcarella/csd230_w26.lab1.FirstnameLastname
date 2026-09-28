package csd230.lab1.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.util.Objects;

/**
 * Concrete entity representing a SmartPhone stored in the database.
 */
@Entity
@DiscriminatorValue("SMARTPHONE")
public class SmartPhoneEntity extends ElectronicProductEntity {

    private double screenSizeInches;

    /**
     * Default constructor required by JPA.
     */
    public SmartPhoneEntity() {
        super();
    }

    /**
     * Parameterized constructor.
     *
     * @param price            Product price
     * @param description      Product description
     * @param brand            Brand name
     * @param screenSizeInches Display size in inches
     */
    public SmartPhoneEntity(double price, String description, String brand, double screenSizeInches) {
        super(price, description, brand);
        this.screenSizeInches = screenSizeInches;
    }

    // Getters and Setters

    public double getScreenSizeInches() {
        return screenSizeInches;
    }

    public void setScreenSizeInches(double screenSizeInches) {
        this.screenSizeInches = screenSizeInches;
    }

    /**
     * Implementation of the sellItem method from SaleableItem interface.
     */
    @Override
    public void sellItem() {
        // Business logic for selling a smartphone
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SmartPhoneEntity that = (SmartPhoneEntity) o;
        return Double.compare(that.screenSizeInches, screenSizeInches) == 0 && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), screenSizeInches);
    }

    @Override
    public String toString() {
        return "SmartPhoneEntity{" +
                "screenSizeInches=" + screenSizeInches +
                "} " + super.toString();
    }
}