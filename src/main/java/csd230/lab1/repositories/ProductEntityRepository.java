package csd230.lab1.repositories;

import csd230.lab1.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductEntityRepository extends JpaRepository<ProductEntity, Long> {

    // Find by exact price
    List<ProductEntity> findByPrice(double price);

    // Find products cheaper than a value
    List<ProductEntity> findByPriceLessThan(double price);

    // Find products more expensive than a value
    List<ProductEntity> findByPriceGreaterThan(double price);

    // Like query (works if ProductEntity has title or description)
    List<ProductEntity> findByTitleLike(String pattern);

    // Alternative if ProductEntity uses description instead of title
    List<ProductEntity> findByDescriptionLike(String pattern);
}
