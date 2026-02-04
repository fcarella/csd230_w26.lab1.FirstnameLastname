package csd230.lab1.repositories;

import csd230.lab1.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.List;

public interface OrderEntityRepository extends JpaRepository<OrderEntity, Long> {

    // Find orders by date range
    List<OrderEntity> findByOrderDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    // Find orders with total amount greater than specified value
    List<OrderEntity> findByTotalAmountGreaterThan(double amount);

    // Find orders with total amount less than specified value
    List<OrderEntity> findByTotalAmountLessThan(double amount);

    // Find orders by specific date (ignoring time)
    @Query("SELECT o FROM OrderEntity o WHERE DATE(o.orderDate) = DATE(:date)")
    List<OrderEntity> findByOrderDate(@Param("date") LocalDateTime date);

    // Find orders containing a specific product
    @Query("SELECT o FROM OrderEntity o JOIN o.products p WHERE p.id = :productId")
    List<OrderEntity> findByProductId(@Param("productId") Long productId);

    // Find orders with minimum product count
    @Query("SELECT o FROM OrderEntity o WHERE SIZE(o.products) >= :minProductCount")
    List<OrderEntity> findByMinProductCount(@Param("minProductCount") int minProductCount);

    // Find orders sorted by total amount in descending order
    List<OrderEntity> findByOrderByTotalAmountDesc();

    // Find orders sorted by date in descending order (most recent first)
    List<OrderEntity> findByOrderByOrderDateDesc();
}
