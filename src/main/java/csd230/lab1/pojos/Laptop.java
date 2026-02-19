package csd230.lab1.pojos;

public class Laptop extends Electronics {

    private int ramGB;

    public Laptop() {}

    public Laptop(String title, double price, int warrantyMonths, int ramGB) {
        super(title, price, warrantyMonths);
        this.ramGB = ramGB;
    }

    public int getRamGB() {
        return ramGB;
    }

    public void setRamGB(int ramGB) {
        this.ramGB = ramGB;
    }

    // ===============================
    // Required interface methods
    // ===============================

    @Override
    public void edit() {
        // simulate upgrade
        this.ramGB += 8;
        System.out.println("Laptop upgraded. RAM is now " + ramGB + "GB");
    }

    @Override
    public void initialize() {
        if (getTitle() == null || getTitle().isBlank()) {
            setTitle("Unnamed Laptop");
        }

        if (getPrice() <= 0) {
            setPrice(1.00);
        }

        if (ramGB <= 0) {
            ramGB = 8;
        }

        System.out.println("Laptop initialized: " + getTitle() + " (" + ramGB + "GB RAM)");
    }

    @Override
    public void sellItem() {
        System.out.println("Sold laptop: " + getTitle() +
                " | RAM: " + ramGB + "GB | Price: $" + getPrice());
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "ramGB=" + ramGB +
                ", " + super.toString() +
                "}";
    }
}
