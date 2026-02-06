package csd230.lab1.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("SMARTPHONE")
public class SmartphoneEntity extends ElectronicEntity {

    private int cameraMegapixels;

    public SmartphoneEntity() {}

    public SmartphoneEntity(String brand, int cameraMegapixels) {
        super(brand);
        this.cameraMegapixels = cameraMegapixels;
    }

    public int getCameraMegapixels() {
        return cameraMegapixels;
    }

    public void setCameraMegapixels(int cameraMegapixels) {
        this.cameraMegapixels = cameraMegapixels;
    }

    @Override
    public String toString() {
        return "SmartphoneEntity{" +
                "id=" + getId() +
                ", brand='" + getBrand() + '\'' +
                ", cameraMegapixels=" + cameraMegapixels +
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
