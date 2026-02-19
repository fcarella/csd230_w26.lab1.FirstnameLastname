package csd230.lab1.pojos;

import java.io.Serializable;

// Marked abstract because it implements SaleableItem but doesn't implement getPrice()
// (Price is defined in children: Ticket and Publication)
public abstract class Product extends Editable implements SaleableItem, Serializable {
    private String productId;
    private String title;
    private double price;

    protected Product() {}

    public Product(String title, double price) {
        this.title = title;
        this.price = price;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    @Override
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
