package com.portfolio.cafeorderservice.order;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CafeOrderRepository extends JpaRepository<CafeOrder, Long> {

    List<CafeOrder> findByOrderedAtBetweenAndStatusNot(
            LocalDateTime start,
            LocalDateTime end,
            OrderStatus status
    );
}