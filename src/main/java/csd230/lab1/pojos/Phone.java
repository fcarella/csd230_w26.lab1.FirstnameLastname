package csd230.lab1.pojos;

public class Phone extends Electronics {

    private boolean has5g;
    private double price;

    public Phone() {}

    public Phone(String brand, boolean has5g, double price) {
        super(brand);
        this.has5g = has5g;
        this.price = price;
    }

    public boolean isHas5g() {
        return has5g;
    }

    public void setHas5g(boolean has5g) {
        this.has5g = has5g;
    }

    @Override
    public void sellItem() {
        System.out.println("Selling Phone (" + getBrand() + ", 5G=" + has5g + ") for $" + price);
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
        System.out.println("Initializing Phone");
    }

    // ✅ REQUIRED by Editable
    @Override
    public void edit() {
        System.out.println("Editing Phone details");
    }
}
