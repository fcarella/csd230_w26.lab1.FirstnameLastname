package csd230.lab1.pojos;

import java.io.Serializable;

public abstract class Product implements SaleableItem, Serializable {
    private String productId;
    private String title;
    private double price;
    private int quantity;
    private String description;

    // Constructors
    public Product() {}

    public Product(String productId, String title, double price, int quantity, String description) {
        this.productId = productId;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
    }

    // Getters and Setters
    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    @Override
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    @Override
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    @Override
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    @Override
    public boolean sell(int quantity) {
        if (this.quantity >= quantity) {
            this.quantity -= quantity;
            return true;
        }
        return false;
    }

    // NO initialize() or edit() methods - we're removing Editable functionality
}