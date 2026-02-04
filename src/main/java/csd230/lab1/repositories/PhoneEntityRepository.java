package csd230.lab1.repositories;

import csd230.lab1.entities.PhoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhoneEntityRepository extends JpaRepository<PhoneEntity, Long> {
    List<PhoneEntity> findByHas5gTrue();
}
