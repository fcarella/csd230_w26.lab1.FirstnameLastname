package csd230.lab1.repositories;

import csd230.lab1.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing base ProductEntity instances.
 */
@Repository
public interface ProductEntityRepository extends JpaRepository<ProductEntity, Long> {
}