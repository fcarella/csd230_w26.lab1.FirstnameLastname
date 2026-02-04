package csd230.lab1.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "order_entity")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "total_amount", nullable = false)
    private double totalAmount;

    @Column(name = "order_date", nullable = false)
    private LocalDateTime orderDate;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "order_products",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"order_id", "product_id"})
    )
    private Set<ProductEntity> products = new LinkedHashSet<>();

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

    public void addProduct(ProductEntity product) {
        if (product == null) return;
        this.products.add(product);
        // Order doesn't need to add itself to product's carts
        // If you need a separate relationship, you can add:
        // product.getOrders().add(this);
    }

    public void removeProduct(ProductEntity product) {
        if (product == null) return;
        this.products.remove(product);
        // If you added orders to ProductEntity, you'd need:
        // product.getOrders().remove(this);
    }

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
        // Clear existing products without modifying ProductEntity's carts
        this.products.clear();

        if (products != null) {
            this.products.addAll(products);
            // If you had orders in ProductEntity, you'd need:
            // for (ProductEntity product : products) {
            //     product.getOrders().add(this);
            // }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderEntity that = (OrderEntity) o;
        return Double.compare(totalAmount, that.totalAmount) == 0 &&
                Objects.equals(id, that.id) &&
                Objects.equals(orderDate, that.orderDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, totalAmount, orderDate);
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "id=" + id +
                ", totalAmount=" + totalAmount +
                ", orderDate=" + orderDate +
                ", products=" + products.size() +
                '}';
    }
}