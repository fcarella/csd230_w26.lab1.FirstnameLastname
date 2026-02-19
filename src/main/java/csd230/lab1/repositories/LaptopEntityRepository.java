package csd230.lab1.repositories;

import csd230.lab1.entities.LaptopEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LaptopEntityRepository extends JpaRepository<LaptopEntity, Long> {
    List<LaptopEntity> findByRamGbGreaterThan(int ramGb);
}
