package csd230.lab1.pojos;

// Concrete class for smartphones
public class Smartphone extends Electronic {

    // Unique field for smartphones
    private int cameraMegapixels;

    public Smartphone() {
        // empty constructor
    }

    public Smartphone(String brand, int cameraMegapixels) {
        super(brand);
        this.cameraMegapixels = cameraMegapixels;
    }

    public int getCameraMegapixels() {
        return cameraMegapixels;
    }

    public void setCameraMegapixels(int cameraMegapixels) {
        this.cameraMegapixels = cameraMegapixels;
    }

    @Override
    public void edit() {

    }

    @Override
    public void initialize() {

    }

    @Override
    public String toString() {
        return "Smartphone{" +
                "brand='" + getBrand() + '\'' +
                ", cameraMegapixels=" + cameraMegapixels +
                "} " + super.toString();
    }

    @Override
    public void sellItem() {

    }

    @Override
    public double getPrice() {
        return 0;
    }
}
