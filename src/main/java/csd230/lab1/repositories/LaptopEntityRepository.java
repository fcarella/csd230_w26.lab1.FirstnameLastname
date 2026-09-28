package csd230.lab1.repositories;

import csd230.lab1.entities.LaptopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing LaptopEntity database operations.
 */
@Repository
public interface LaptopEntityRepository extends JpaRepository<LaptopEntity, Long> {

    /**
     * Derived query to find laptops by brand name.
     *
     * @param brand The brand name
     * @return List of matching LaptopEntities
     */
    List<LaptopEntity> findByBrand(String brand);
}