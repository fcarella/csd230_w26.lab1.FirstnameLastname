package csd230.lab1.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PHONE")
public class PhoneEntity extends ElectronicsEntity {

    private Boolean has5g;

    public PhoneEntity() {}

    public PhoneEntity(String title, double price, Integer warrantyMonths, Boolean has5g) {
        super(title, price, warrantyMonths);
        this.has5g = has5g;
    }

    public Boolean getHas5g() { return has5g; }
    public void setHas5g(Boolean has5g) { this.has5g = has5g; }

    @Override
    public void sellItem() {
        System.out.println("Sold Phone: " + getTitle() + " (5G=" + has5g + ")");
    }

    @Override
    public String toString() {
        return "PhoneEntity{has5g=" + has5g + ", " + super.toString() + "}";
    }
}
