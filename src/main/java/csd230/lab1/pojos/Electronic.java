package csd230.lab1.pojos;

// Abstract class for all electronic products in the store
public abstract class Electronic extends Product {

    // Common field for all electronics
    private String brand;

    public Electronic() {
        // empty constructor for frameworks and easy use
    }

    public Electronic(String brand) {
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "Electronic{" +
                "brand='" + brand + '\'' +
                "} " + super.toString();
    }
}
