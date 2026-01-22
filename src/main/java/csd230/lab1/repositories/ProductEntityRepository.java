package csd230.lab1.repositories;

import csd230.lab1.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ProductEntityRepository extends JpaRepository<ProductEntity, Long> {


    @Query("SELECT p FROM ProductEntity p WHERE TYPE(p) = :type")
    List<ProductEntity> findByProductType(@Param("type") Class<? extends ProductEntity> type);


    @Query("SELECT p FROM ProductEntity p WHERE p.getPrice() BETWEEN :minPrice AND :maxPrice")
    List<ProductEntity> findAllByPriceRange(@Param("minPrice") double minPrice,
                                            @Param("maxPrice") double maxPrice);
}