package csd230.lab1.entities;

import jakarta.persistence.MappedSuperclass;
import java.util.Objects;

@MappedSuperclass
public abstract class PublicationEntity extends ProductEntity {
    private int copies;

    public PublicationEntity() {}

    public PublicationEntity(String title, String description, double price, int copies) {
        super(title, description, price, copies);
        this.copies = copies;
    }

    @Override
    public void sellItem() {
        if (copies > 0) {
            copies--;
            setQuantity(copies);
            System.out.println("Sold '" + getTitle() + "'. Remaining copies: " + copies);
        } else {
            System.out.println("Cannot sell '" + getTitle() + "'. Out of stock.");
        }
    }

    @Override
    public boolean sell(int quantity) {
        if (copies >= quantity) {
            copies -= quantity;
            // Also update the inherited quantity field
            setQuantity(copies);
            return true;
        }
        return false;
    }

    // Getters and setters
    public int getCopies() { return copies; }
    public void setCopies(int c) {
        this.copies = c;
        // Also update the inherited quantity field
        setQuantity(c);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PublicationEntity that = (PublicationEntity) o;
        return copies == that.copies;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), copies);
    }

    @Override
    public String toString() {
        return "Pub{title='" + getTitle() + "', price=" + getPrice() + ", copies=" + copies + "}";
    }
}