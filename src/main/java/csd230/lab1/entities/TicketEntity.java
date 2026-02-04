package csd230.lab1.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("TICKET")
public class TicketEntity extends ProductEntity {

    public TicketEntity() {}

    public TicketEntity(String title, double price, String description) {
        super(title, price);
        setDescription(description);
    }

    @Override
    public void sellItem() {
        System.out.println("Selling Ticket: " + getTitle() + " for $" + getPrice());
    }

    @Override
    public String toString() {
        return "Ticket{title='" + getTitle() + "', price=" + getPrice() + ", desc='" + getDescription() + "'}";
    }
}
