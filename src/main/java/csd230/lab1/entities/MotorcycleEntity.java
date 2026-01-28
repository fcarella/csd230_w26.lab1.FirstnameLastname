package csd230.lab1.entities;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("MOTORCYCLE")  // For SINGLE_TABLE inheritance
// NO @Table annotation here!
public class MotorcycleEntity extends VehicleEntity {

    @Column(name = "has_sidecar")
    private boolean hasSidecar;

    // Implement abstract methods from ProductEntity
    @Override
    public void sellItem() {
        if (getQuantity() > 0) {
            setQuantity(getQuantity() - 1);
        }
    }

    @Override
    public boolean sell(int quantity) {
        if (getQuantity() >= quantity) {
            setQuantity(getQuantity() - quantity);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "MotorcycleEntity{" +
                "id=" + getId() +
                ", hasSidecar=" + hasSidecar +
                ", engineType='" + getEngineType() + '\'' +
                ", title='" + getTitle() + '\'' +
                ", price=" + getPrice() +
                ", quantity=" + getQuantity() +
                ", description='" + getDescription() + '\'' +
                '}';
    }

    public boolean getHasSidecar() {
        return hasSidecar;
    }

    public void setHasSidecar(boolean hasSidecar) {
        this.hasSidecar = hasSidecar;
    }
}