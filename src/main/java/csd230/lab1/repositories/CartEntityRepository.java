package csd230.lab1.repositories;

import csd230.lab1.entities.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartEntityRepository extends JpaRepository<CartEntity, Long> {

    // Se o CartEntity tiver algum campo tipo "name" ou "cartId", você pode criar depois:
    // List<CartEntity> findByCartId(String cartId);
}
