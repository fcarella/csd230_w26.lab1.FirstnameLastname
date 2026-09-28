package csd230.lab1.pojos;

/**
 * Concrete class representing a SmartPhone product.
 * Extends ElectronicProduct to add smartphone-specific properties such as screen size.
 */
public class SmartPhone extends ElectronicProduct {
    private double screenSizeInches;

    /**
     * Default no-argument constructor.
     */
    public SmartPhone() {
        super();
    }

    /**
     * Parameterized constructor for SmartPhone.
     *
     * @param price            The price of the smartphone
     * @param description      The description of the smartphone
     * @param brand            The brand name
     * @param screenSizeInches Display screen size in inches
     */
    public SmartPhone(double price, String description, String brand, double screenSizeInches) {
        super(price, description, brand);
        this.screenSizeInches = screenSizeInches;
    }

    public double getScreenSizeInches() {
        return screenSizeInches;
    }

    public void setScreenSizeInches(double screenSizeInches) {
        this.screenSizeInches = screenSizeInches;
    }

    /**
     * Implementation of sellItem method from SaleableItem interface.
     */
    @Override
    public void sellItem() {
        System.out.println("Selling SmartPhone: " + getDescription() + " (" + screenSizeInches + "\") for $" + getPrice());
    }

    /**
     * Interactive edit logic for SmartPhone attributes.
     */
    @Override
    public void edit() {
        super.edit();
        System.out.print("Enter Screen Size in Inches (" + getScreenSizeInches() + "): ");
        setScreenSizeInches(getInput(getScreenSizeInches()));
    }

    /**
     * Interactive initialization logic for SmartPhone attributes.
     */
    @Override
    public void initialize() {
        super.initialize();
        System.out.print("Enter Screen Size in Inches: ");
        setScreenSizeInches(getInput(0.0));
    }

    @Override
    public String toString() {
        return "SmartPhone{" +
                "screenSizeInches=" + screenSizeInches +
                "} " + super.toString();
    }
}