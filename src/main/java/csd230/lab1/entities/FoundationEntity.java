package csd230.lab1.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.util.Objects;

@Entity
@DiscriminatorValue("FOUNDATION")
public class FoundationEntity extends AbstractCosmeticsEntity {
    private String coverageLevel;

    @Column(name = "foundation_price")
    private double price;

    public FoundationEntity() {}

    public FoundationEntity(String skinType, String coverageLevel, double price) {
        super(skinType);
        this.coverageLevel = coverageLevel;
        this.price = price;
    }

    public String getCoverageLevel() {
        return coverageLevel;
    }

    public void setCoverageLevel(String coverageLevel) {
        this.coverageLevel = coverageLevel;
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
        System.out.println("Selling Foundation ID: " + getId() +
                " with " + coverageLevel + " coverage" +
                " for " + getSkinType() + " skin" +
                " at price: $" + price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FoundationEntity that = (FoundationEntity) o;
        return Double.compare(price, that.price) == 0 &&
                Objects.equals(coverageLevel, that.coverageLevel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), coverageLevel, price);
    }

    @Override
    public String toString() {
        return "FoundationEntity{id=" + getId() +
                ", skinType='" + getSkinType() +
                "', coverageLevel='" + coverageLevel +
                "', price=" + price + "}";
    }
}