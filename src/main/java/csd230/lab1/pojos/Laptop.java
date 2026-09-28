package csd230.lab1.pojos;

/**
 * Concrete class representing a Laptop product.
 * Extends ElectronicProduct to add laptop-specific properties such as RAM capacity.
 */
public class Laptop extends ElectronicProduct {
    private int ramGb;

    /**
     * Default no-argument constructor.
     */
    public Laptop() {
        super();
    }

    /**
     * Parameterized constructor for Laptop.
     *
     * @param price       The price of the laptop
     * @param description The description of the laptop
     * @param brand       The brand name
     * @param ramGb       RAM size in Gigabytes
     */
    public Laptop(double price, String description, String brand, int ramGb) {
        super(price, description, brand);
        this.ramGb = ramGb;
    }

    public int getRamGb() {
        return ramGb;
    }

    public void setRamGb(int ramGb) {
        this.ramGb = ramGb;
    }

    /**
     * Implementation of sellItem method from SaleableItem interface.
     */
    @Override
    public void sellItem() {
        System.out.println("Selling Laptop: " + getDescription() + " (RAM: " + ramGb + "GB) for $" + getPrice());
    }

    /**
     * Interactive edit logic for Laptop attributes.
     */
    @Override
    public void edit() {
        super.edit();
        System.out.print("Enter RAM in GB (" + getRamGb() + "): ");
        setRamGb(getInput(getRamGb()));
    }

    /**
     * Interactive initialization logic for Laptop attributes.
     */
    @Override
    public void initialize() {
        super.initialize();
        System.out.print("Enter RAM in GB: ");
        setRamGb(getInput(0));
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "ramGb=" + ramGb +
                "} " + super.toString();
    }
}