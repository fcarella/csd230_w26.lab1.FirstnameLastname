package csd230.lab1.pojos;

public abstract class Electronics extends Product {
    private String brand;

    public Electronics() {}
    public Electronics(String brand) { this.brand = brand; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
}
