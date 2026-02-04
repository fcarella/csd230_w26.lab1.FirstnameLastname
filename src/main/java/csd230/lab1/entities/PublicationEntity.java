package csd230.lab1.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

import java.util.Objects;

@MappedSuperclass
public abstract class PublicationEntity extends ProductEntity {
    private String title;

    @Column(name = "pub_price")
    private double price;

    private int copies;

    public PublicationEntity() {}

    public PublicationEntity(String t, double p, int c) {
        this.title = t;
        this.price = p;
        this.copies = c;
    }

    @Override
    public void sellItem() {
        if (copies > 0) {
            copies--;
            System.out.println("Sold '" + title + "'. Remaining copies: " + copies);
        } else {
            System.out.println("Cannot sell '" + title + "'. Out of stock.");
        }
    }

    @Override
    public double getPrice() { return price; }

    public String getTitle() { return title; }
    public void setTitle(String t) { this.title = t; }
    public void setPrice(double p) { this.price = p; }
    public int getCopies() { return copies; }
    public void setCopies(int c) { this.copies = c; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PublicationEntity that = (PublicationEntity) o;
        return Double.compare(price, that.price) == 0 &&
                copies == that.copies &&
                Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title, price, copies);
    }

    @Override
    public String toString() {
        return "Publication{title='" + title + "', price=" + price + ", copies=" + copies + "}";
    }
}