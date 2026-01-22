package csd230.lab1.entities;

import jakarta.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "cart_entity")
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;



    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "cart_products",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"cart_id", "product_id"})
    )
    private Set<ProductEntity> products = new LinkedHashSet<>();

    public CartEntity() {}

    public void addProduct(ProductEntity product) {
        if (product == null) return;
        this.products.add(product);
        product.getCarts().add(this);
    }

    public void removeProduct(ProductEntity product) {
        if (product == null) return;
        this.products.remove(product);
        product.getCarts().remove(this);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Set<ProductEntity> getProducts() { return products; }

    public void setProducts(Set<ProductEntity> products) {
        if (this.products != null) {
            for (ProductEntity product : this.products) {
                product.getCarts().remove(this);  // Accessing bidirectional relationship
            }
        }
        this.products = products;
        if (products != null) {
            for (ProductEntity product : products) {
                product.getCarts().add(this);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartEntity that = (CartEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "CartEntity{id=" + id + ", products=" + products.size() + "}";
    }
}