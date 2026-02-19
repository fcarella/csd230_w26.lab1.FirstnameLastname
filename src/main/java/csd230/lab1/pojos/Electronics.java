package csd230.lab1.pojos;

public abstract class Electronics extends Product {
    private int warrantyMonths;

    protected Electronics() {}

    protected Electronics(String title, double price, int warrantyMonths) {
        super(title, price);
        this.warrantyMonths = warrantyMonths;
    }

    public int getWarrantyMonths() { return warrantyMonths; }
    public void setWarrantyMonths(int warrantyMonths) { this.warrantyMonths = warrantyMonths; }

    @Override
    public String toString() {
        return "Electronics{title='" + getTitle() + "', price=" + getPrice() +
                ", warrantyMonths=" + warrantyMonths + "}";
    }
}
