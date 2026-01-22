package csd230.lab1.repositories;

import csd230.lab1.entities.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface TicketEntityRepository extends JpaRepository<TicketEntity, Long> {


    List<TicketEntity> findByDescription(String description);

    List<TicketEntity> findByDescriptionLike(String descriptionPattern);

    List<TicketEntity> findByPrice(double price);

    List<TicketEntity> findByPriceGreaterThan(double price);

    List<TicketEntity> findByPriceLessThan(double price);


    @Query("SELECT t FROM TicketEntity t WHERE UPPER(t.description) LIKE UPPER(CONCAT('%', :keyword, '%'))")
    List<TicketEntity> findByDescriptionContainingIgnoreCase(@Param("keyword") String keyword);
}