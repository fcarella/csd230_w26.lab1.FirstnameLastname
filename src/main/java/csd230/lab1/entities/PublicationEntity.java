package csd230.lab1.entities;

import jakarta.persistence.Entity;

@Entity
public abstract class PublicationEntity extends ProductEntity {

    private int copies;

    protected PublicationEntity() {}

    protected PublicationEntity(String title, double price, int copies) {
        super(title, price);
        this.copies = copies;
    }

    @Override
    public void sellItem() {
        if (copies > 0) {
            copies--;
            System.out.println("Sold '" + getTitle() + "'. Remaining copies: " + copies);
        } else {
            System.out.println("Cannot sell '" + getTitle() + "'. Out of stock.");
        }
    }

    public int getCopies() { return copies; }
    public void setCopies(int copies) { this.copies = copies; }

    @Override
    public String toString() {
        return "Pub{title='" + getTitle() + "', price=" + getPrice() + ", copies=" + copies + "}";
    }
}
