package csd230.lab1.entities;
import csd230.lab1.pojos.SaleableItem;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "products")  // ONLY root class has @Table in SINGLE_TABLE
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "product_type", discriminatorType = DiscriminatorType.STRING)
public abstract class ProductEntity implements Serializable, SaleableItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public ProductEntity() {}

    public ProductEntity(String title, String descriptiion, double price, int quantity) {
        this.title = title;
        this.description = descriptiion;
        this.price = price;
        this.quantity = quantity;
    }
    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private double price;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "description", length = 1000)
    private String description;

    @ManyToMany(mappedBy = "products")
    private Set<CartEntity> carts = new HashSet<>();

    // GETTERS AND SETTERS:
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Set<CartEntity> getCarts() { return carts; }
    public void setCarts(Set<CartEntity> carts) { this.carts = carts; }
    public String getProductType() {
       return this.getClass().getSimpleName();
   }

    @Override
    public abstract void sellItem();

    @Override
    public abstract boolean sell(int quantity);

    @Override
    public String toString() {
        return "ProductEntity{id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", quantity=" + quantity + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity that = (ProductEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(title, that.title) &&
                Double.compare(price, that.price) == 0 &&
                quantity == that.quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, price, quantity);
    }
}