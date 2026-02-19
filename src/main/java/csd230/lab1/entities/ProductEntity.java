package csd230.lab1.entities;

import csd230.lab1.pojos.SaleableItem;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "products")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "product_type", discriminatorType = DiscriminatorType.STRING)
public abstract class ProductEntity implements Serializable, SaleableItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(nullable = false)
    private double price;

    @Column(length = 1000)
    private String description;

    protected ProductEntity() {}
    protected ProductEntity(String title, double price) {
        this.title = title;
        this.price = price;
    }
    @ManyToMany(mappedBy = "products")
    private Set<CartEntity> carts = new HashSet<>();

    public Set<CartEntity> getCarts() {
        return carts;
    }

    public void setCarts(Set<CartEntity> carts) {
        this.carts = carts;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    @Override
    public double getPrice() { return price; }   // because SaleableItem likely requires it
    public void setPrice(double price) { this.price = price; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getProductType() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "id=" + id +
                "} : "+super.toString();
    }
}