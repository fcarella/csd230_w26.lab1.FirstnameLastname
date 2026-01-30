package csd230.lab1.repositories;

import csd230.lab1.entities.MotorcycleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MotorcycleRepository extends JpaRepository<MotorcycleEntity, Long> {
}