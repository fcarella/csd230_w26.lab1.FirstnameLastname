package csd230.lab1.entities;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class ElectronicEntity extends ProductEntity {

    private String brand;

    public ElectronicEntity() {}

    public ElectronicEntity(String brand) {
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "ElectronicEntity{" +
                "id=" + getId() +
                ", brand='" + brand + '\'' +
                '}';
    }
}
