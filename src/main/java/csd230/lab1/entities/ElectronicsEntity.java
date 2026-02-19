package csd230.lab1.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("ELECTRONICS")
public abstract class ElectronicsEntity extends ProductEntity {

    private int warrantyMonths;

    protected ElectronicsEntity() {}

    protected ElectronicsEntity(String title, double price, int warrantyMonths) {
        super(title, price);
        this.warrantyMonths = warrantyMonths;
    }

    public int getWarrantyMonths() { return warrantyMonths; }
    public void setWarrantyMonths(int warrantyMonths) { this.warrantyMonths = warrantyMonths; }
}

