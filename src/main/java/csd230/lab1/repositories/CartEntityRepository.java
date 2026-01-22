package csd230.lab1.repositories;

import csd230.lab1.entities.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface CartEntityRepository extends JpaRepository<CartEntity, Long> {


    @Query("SELECT c FROM CartEntity c JOIN c.products p WHERE p.id = :productId")
    List<CartEntity> findByProductId(@Param("productId") Long productId);


    @Query("SELECT c FROM CartEntity c WHERE SIZE(c.products) > :minProducts")
    List<CartEntity> findByProductCountGreaterThan(@Param("minProducts") int minProducts);


    @Query("SELECT c FROM CartEntity c WHERE SIZE(c.products) = :productCount")
    List<CartEntity> findByExactProductCount(@Param("productCount") int productCount);
}