package csd230.lab1.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PHONE")
public class PhoneEntity extends ElectronicsEntity {

    private boolean has5g;

    public PhoneEntity() {}

    public PhoneEntity(String brand, double price, boolean has5g) {
        super(brand, price);
        this.has5g = has5g;
    }

    public boolean isHas5g() {
        return has5g;
    }

    public void setHas5g(boolean has5g) {
        this.has5g = has5g;
    }

    @Override
    public String toString() {
        return super.toString() + ", has5g=" + has5g;
    }
}
