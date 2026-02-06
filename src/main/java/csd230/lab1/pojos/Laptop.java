package csd230.lab1.pojos;

// Concrete class for laptops
public class Laptop extends Electronic {

    // Unique field for laptops
    private int ramGb;

    public Laptop() {
        // empty constructor
    }

    public Laptop(String brand, int ramGb) {
        super(brand);
        this.ramGb = ramGb;
    }

    public int getRamGb() {
        return ramGb;
    }

    public void setRamGb(int ramGb) {
        this.ramGb = ramGb;
    }

    @Override
    public void edit() {

    }

    @Override
    public void initialize() {

    }

    @Override
    public String toString() {
        return "Laptop{" +
                "brand='" + getBrand() + '\'' +
                ", ramGb=" + ramGb +
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
