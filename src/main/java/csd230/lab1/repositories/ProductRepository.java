package csd230.lab1.repositories;

import csd230.lab1.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @Override
    List<ProductEntity> findAll();
}