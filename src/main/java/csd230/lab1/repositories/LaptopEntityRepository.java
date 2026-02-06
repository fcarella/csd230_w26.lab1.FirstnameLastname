package csd230.lab1.repositories;

import csd230.lab1.entities.LaptopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LaptopEntityRepository extends JpaRepository<LaptopEntity, Long> {

    // Simple derived query (optional but useful)
    List<LaptopEntity> findByBrand(String brand);
}
