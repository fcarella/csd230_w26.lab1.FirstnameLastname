package csd230.lab1.repositories;

import csd230.lab1.entities.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketEntityRepository extends JpaRepository<TicketEntity, Long> {

    // todos os tickets com preço menor que X
    List<TicketEntity> findByPriceLessThan(double price);

    // busca por parte da descrição
    List<TicketEntity> findByDescriptionContainingIgnoreCase(String descriptionPart);
}
