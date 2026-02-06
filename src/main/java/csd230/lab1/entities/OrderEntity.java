package csd230.lab1.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createdAt;

    private double totalAmount;

    @ManyToMany
    @JoinTable(
            name = "order_products",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<ProductEntity> products = new HashSet<>();

    // Constructor
    public OrderEntity() {
        this.createdAt = LocalDateTime.now();
    }

    // Business helper
    public void addProduct(ProductEntity product) {
        products.add(product);
        totalAmount += product.getPrice();
    }

    // Getters
    public Long getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public Set<ProductEntity> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
