package csd230.lab1.pojos;

/**
 * Abstract class representing an electronic product.
 * Inherits from Product and provides fields for price, description, and brand.
 */
public abstract class ElectronicProduct extends Product {
    private double price;
    private String description;
    private String brand;

    /**
     * Default no-argument constructor.
     */
    public ElectronicProduct() {
        super();
    }

    /**
     * Parameterized constructor to initialize price, description, and brand.
     *
     * @param price       The price of the electronic product
     * @param description The description of the electronic product
     * @param brand       The brand name of the electronic product
     */
    public ElectronicProduct(double price, String description, String brand) {
        super();
        this.price = price;
        this.description = description;
        this.brand = brand;
    }

    /**
     * Returns the price of the electronic product as required by SaleableItem.
     *
     * @return The product price
     */
    @Override
    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Prompts the user to edit existing fields interactively via CLI.
     */
    @Override
    public void edit() {
        System.out.print("Enter Product ID (" + getProductId() + "): ");
        setProductId(getInput(getProductId()));

        System.out.print("Enter Description (" + getDescription() + "): ");
        setDescription(getInput(getDescription()));

        System.out.print("Enter Price (" + getPrice() + "): ");
        setPrice(getInput(getPrice()));

        System.out.print("Enter Brand (" + getBrand() + "): ");
        setBrand(getInput(getBrand()));
    }

    /**
     * Prompts the user to initialize a new instance interactively via CLI.
     */
    @Override
    public void initialize() {
        System.out.print("Enter Product ID: ");
        setProductId(getInput(""));

        System.out.print("Enter Description: ");
        setDescription(getInput(""));

        System.out.print("Enter Price: ");
        setPrice(getInput(0.0));

        System.out.print("Enter Brand: ");
        setBrand(getInput(""));
    }

    @Override
    public String toString() {
        return "ElectronicProduct{" +
                "productId='" + getProductId() + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", brand='" + brand + '\'' +
                '}';
    }
}