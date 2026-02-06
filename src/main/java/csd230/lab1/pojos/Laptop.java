package csd230.lab1.pojos;

public class Laptop extends Electronics {

    private int ramGb;
    private double price;

    public Laptop() {}

    public Laptop(String brand, int ramGb, double price) {
        super(brand);
        this.ramGb = ramGb;
        this.price = price;
    }

    public int getRamGb() {
        return ramGb;
    }

    public void setRamGb(int ramGb) {
        this.ramGb = ramGb;
    }

    @Override
    public void sellItem() {
        System.out.println("Selling Laptop (" + getBrand() + ", " + ramGb + "GB) for $" + price);
    }

    @Override
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // ✅ REQUIRED by Editable
    @Override
    public void initialize() {
        System.out.println("Initializing Laptop");
    }

    // ✅ REQUIRED by Editable
    @Override
    public void edit() {
        System.out.println("Editing Laptop details");
    }
}
