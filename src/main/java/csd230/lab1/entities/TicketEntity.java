package csd230.lab1.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.util.Objects;

@Entity
@DiscriminatorValue("TICKET")
public class TicketEntity extends ProductEntity {

    public TicketEntity() {}

    // FIXED CONSTRUCTOR: Use parent's fields
    public TicketEntity(String title, String description, double price, int quantity) {
        this.setTitle(title);
        this.setDescription(description);  // Use setter for parent's field
        this.setPrice(price);              // Use setter for parent's field
        this.setQuantity(quantity);
    }

    // Alternative simpler constructor
    public TicketEntity(String description, double price) {
        this.setDescription(description);
        this.setPrice(price);
        this.setTitle("Ticket: " + description);  // Set a default title
    }

    @Override
    public void sellItem() {
        System.out.println("Selling Ticket: " + getDescription() + " for $" + getPrice());
    }

    @Override
    public boolean sell(int quantity) {
        // Implement ticket selling logic
        if (getQuantity() >= quantity) {
            setQuantity(getQuantity() - quantity);
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TicketEntity that = (TicketEntity) o;
        return Double.compare(getPrice(), that.getPrice()) == 0 &&
                Objects.equals(getDescription(), that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getDescription(), getPrice());
    }

    @Override
    public String toString() {
        return "Ticket{desc='" + getDescription() +
                "', price=" + getPrice() +
                ", quantity=" + getQuantity() + "}";
    }
}