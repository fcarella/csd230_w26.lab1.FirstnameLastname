package csd230.lab1.pojos;

public class Phone extends Electronics {
    private boolean has5g;

    public Phone() {}

    public Phone(String title, double price, int warrantyMonths, boolean has5g) {
        super(title, price, warrantyMonths);
        this.has5g = has5g;
    }

    public boolean isHas5g() { return has5g; }
    public void setHas5g(boolean has5g) { this.has5g = has5g; }

    @Override
    public void edit() {
        // Simple "edit" behavior: toggle a feature
        this.has5g = !this.has5g;
        System.out.println("Phone edited: has5g is now " + this.has5g);
    }

    @Override
    public void initialize() {
        // Ensure object is in a valid default state
        if (getTitle() == null || getTitle().isBlank()) {
            setTitle("Unnamed Phone");
        }
        if (getPrice() <= 0) {
            setPrice(1.00);
        }
        System.out.println("Phone initialized: " + getTitle() + " ($" + getPrice() + ")");
    }

    @Override
    public void sellItem() {
        // Demonstrate a "sale" action
        System.out.println("Sold phone: " + getTitle() + " for $" + getPrice());
    }


    @Override
    public String toString() {
        return "Phone{has5g=" + has5g + ", " + super.toString() + "}";
    }

}
