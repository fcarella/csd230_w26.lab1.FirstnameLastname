package csd230.lab1.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.util.Objects;

/**
 * Concrete entity representing a Laptop stored in the database.
 */
@Entity
@DiscriminatorValue("LAPTOP")
public class LaptopEntity extends ElectronicProductEntity {

    private int ramGb;

    /**
     * Default constructor required by JPA.
     */
    public LaptopEntity() {
        super();
    }

    /**
     * Single-argument brand constructor.
     *
     * @param brand Brand name
     * @param ramGb RAM capacity in gigabytes
     */
    public LaptopEntity(String brand, int ramGb) {
        super(brand);
        this.ramGb = ramGb;
    }

    /**
     * Parameterized constructor.
     *
     * @param price       Product price
     * @param description Product description
     * @param brand       Brand name
     * @param ramGb       RAM capacity in gigabytes
     */
    public LaptopEntity(double price, String description, String brand, int ramGb) {
        super(price, description, brand);
        this.ramGb = ramGb;
    }

    // Getters and Setters

    public int getRamGb() {
        return ramGb;
    }

    public void setRamGb(int ramGb) {
        this.ramGb = ramGb;
    }

    @Override
    public void sellItem() {
        // Business logic for selling a laptop
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LaptopEntity that = (LaptopEntity) o;
        return ramGb == that.ramGb && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), ramGb);
    }

    @Override
    public String toString() {
        return "LaptopEntity{" +
                "ramGb=" + ramGb +
                "} " + super.toString();
    }
}