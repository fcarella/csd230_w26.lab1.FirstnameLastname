package csd230.lab1.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.util.Objects;

@Entity
@DiscriminatorValue("LIPSTICK")
public class LipstickEntity extends AbstractCosmeticsEntity {
    private String finishType;

    @Column(name = "lipstick_price")
    private double price;

    public LipstickEntity() {}

    public LipstickEntity(String skinType, String finishType, double price) {
        super(skinType);
        this.finishType = finishType;
        this.price = price;
    }

    public String getFinishType() {
        return finishType;
    }

    public void setFinishType(String finishType) {
        this.finishType = finishType;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public void sellItem() {
        System.out.println("Selling Lipstick ID: " + getId() +
                " with " + finishType + " finish" +
                " for " + getSkinType() + " skin" +
                " at price: $" + price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        LipstickEntity that = (LipstickEntity) o;
        return Double.compare(price, that.price) == 0 &&
                Objects.equals(finishType, that.finishType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), finishType, price);
    }

    @Override
    public String toString() {
        return "LipstickEntity{id=" + getId() +
                ", skinType='" + getSkinType() +
                "', finishType='" + finishType +
                "', price=" + price + "}";
    }
}