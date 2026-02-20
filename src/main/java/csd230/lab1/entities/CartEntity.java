package csd230.lab1.entities;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "carts")
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "cart_products",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<ProductEntity> products = new LinkedHashSet<>();

    // ✅ Lab 3: each cart belongs to one user
    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public CartEntity() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Set<ProductEntity> getProducts() { return products; }
    public void setProducts(Set<ProductEntity> products) { this.products = products; }

    public UserEntity getUser() { return user; }
    public void setUser(UserEntity user) { this.user = user; }

    public void addProduct(ProductEntity product) {
        this.products.add(product);
        product.getCarts().add(this);
    }

    @Override
    public String toString() {
        // ✅ IMPORTANT: do NOT print user here (prevents StackOverflow loops)
        return "CartEntity{id=" + id + ", productsCount=" + (products == null ? 0 : products.size()) + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartEntity other)) return false;
        return id != null && id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
