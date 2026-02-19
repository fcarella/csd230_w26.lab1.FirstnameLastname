package csd230.lab1.repositories;

import csd230.lab1.entities.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartEntityRepository extends JpaRepository<CartEntity, Long> {
    // Optional: add queries only if your CartEntity has searchable attributes (e.g., status, createdAt)
}
