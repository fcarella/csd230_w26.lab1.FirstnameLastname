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

    @Column(name = "total_amount", nullable = false)
    private double totalAmount;

    @Column(name = "order_date", nullable = false)
    private LocalDateTime orderDate;

    @ManyToMany
    @JoinTable(
        name = "order_products",
        joinColumns = @JoinColumn(name = "order_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<ProductEntity> products = new HashSet<>();

    // Constructors
    public OrderEntity() {
        this.orderDate = LocalDateTime.now();
    }

    public OrderEntity(double totalAmount) {
        this.totalAmount = totalAmount;
        this.orderDate = LocalDateTime.now();
    }

    public OrderEntity(double totalAmount, LocalDateTime orderDate) {
        this.totalAmount = totalAmount;
        this.orderDate = orderDate;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public Set<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductEntity> products) {
        this.products = products;
    }

    // Helper methods for managing the relationship
    public void addProduct(ProductEntity product) {
        this.products.add(product);
        // Note: ProductEntity doesn't have a direct reference to OrderEntity,
        // so we don't need to update the inverse side
    }

    public void removeProduct(ProductEntity product) {
        this.products.remove(product);
    }

    // Utility methods
    @Override
    public String toString() {
        return "OrderEntity{" +
                "id=" + id +
                ", totalAmount=" + totalAmount +
                ", orderDate=" + orderDate +
                ", productsCount=" + products.size() +
                '}';
    }
}