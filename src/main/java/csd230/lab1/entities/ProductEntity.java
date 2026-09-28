package csd230.lab1.entities;

import csd230.lab1.pojos.SaleableItem;
import jakarta.persistence.*;

import java.io.Serializable;

import java.util.HashSet;
import java.util.Set;

/**
 * Abstract base entity for all products mapped using SINGLE_TABLE inheritance strategy.
 * Serves as the inverse side of the Many-to-Many relationship with CartEntity.
 */
@Entity
@Table(name = "products")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "product_type", discriminatorType = DiscriminatorType.STRING)
public abstract class ProductEntity implements Serializable, SaleableItem {

    /**
     * Primary key for the product entity.
     */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Inverse side of the Many-to-Many relationship mapped by the "products" field in CartEntity.
     */
    @ManyToMany(mappedBy = "products")
    private Set<CartEntity> carts = new HashSet<>();

    /**
     * Default no-argument constructor required by JPA.
     */
    public ProductEntity() {
    }

    public Long getId() {
        return id;
    }

    public Set<CartEntity> getCarts() {
        return carts;
    }

    public void setCarts(Set<CartEntity> carts) {
        this.carts = carts;
    }

    /**
    public void setId(Long id) {
        this.id = id;
    }
    */


    @Override
    public String toString() {
        return "ProductEntity{id=" + id + "}";
    }
}
