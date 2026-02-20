package csd230.lab1.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("LAPTOP")
public class LaptopEntity extends ElectronicsEntity {

    private int ramGb;

    public LaptopEntity() {}

    public LaptopEntity(String brand, double price, int ramGb) {
        super(brand, price);
        this.ramGb = ramGb;
    }

    public int getRamGb() {
        return ramGb;
    }

    public void setRamGb(int ramGb) {
        this.ramGb = ramGb;
    }

    @Override
    public String toString() {
        return super.toString() + ", ramGb=" + ramGb;
    }
}
