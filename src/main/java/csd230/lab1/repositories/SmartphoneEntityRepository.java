package csd230.lab1.repositories;

import csd230.lab1.entities.SmartphoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SmartphoneEntityRepository extends JpaRepository<SmartphoneEntity, Long> {

    // Simple derived query (optional but useful)
    List<SmartphoneEntity> findByBrand(String brand);
}
