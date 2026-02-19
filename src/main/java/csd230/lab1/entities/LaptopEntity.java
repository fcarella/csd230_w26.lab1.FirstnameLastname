package csd230.lab1.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("LAPTOP")
public class LaptopEntity extends ElectronicsEntity {

    private Integer ramGb;

    public LaptopEntity() {}

    public LaptopEntity(String title, double price, Integer warrantyMonths, Integer ramGb) {
        super(title, price, warrantyMonths);
        this.ramGb = ramGb;
    }

    public int getRamGb() { return ramGb; }
    public void setRamGb(Integer ramGb) { this.ramGb = ramGb; }

    @Override
    public void sellItem() {
        System.out.println("Sold Laptop: " + getTitle() + " (" + ramGb + "GB RAM)");
    }

    @Override
    public String toString() {
        return "LaptopEntity{ramGb=" + ramGb + ", " + super.toString() + "}";
    }
}
