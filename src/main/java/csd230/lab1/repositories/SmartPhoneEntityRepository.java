package csd230.lab1.repositories;

import csd230.lab1.entities.SmartPhoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing SmartPhoneEntity database operations.
 */
@Repository
public interface SmartPhoneEntityRepository extends JpaRepository<SmartPhoneEntity, Long> {

    /**
     * Derived query to find smartphones by brand name.
     *
     * @param brand The brand name
     * @return List of matching SmartPhoneEntities
     */
    List<SmartPhoneEntity> findByBrand(String brand);
}