package csd230.lab1.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("LAPTOP")
public class LaptopEntity extends ElectronicEntity {

    private int ramGb;

    public LaptopEntity() {}

    public LaptopEntity(String brand, int ramGb) {
        super(brand);
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
        return "LaptopEntity{" +
                "id=" + getId() +
                ", brand='" + getBrand() + '\'' +
                ", ramGb=" + ramGb +
                '}';
    }

    @Override
    public void sellItem() {

    }

    @Override
    public double getPrice() {
        return 0;
    }
}
