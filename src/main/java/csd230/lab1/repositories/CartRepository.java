package csd230.lab1.repositories;

import csd230.lab1.entities.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import csd230.lab1.entities.UserEntity;

import java.util.List;

public interface CartRepository extends JpaRepository<CartEntity, Long> {

    // Find the cart belonging to a specific user
    CartEntity findByUser(UserEntity user);

}