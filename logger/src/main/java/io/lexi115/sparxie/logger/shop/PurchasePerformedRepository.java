package io.lexi115.sparxie.logger.shop;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface PurchasePerformedRepository extends JpaRepository<PurchasePerformed, UUID> {
    @Query("SELECT p FROM PurchasePerformed p WHERE p.playerId = :playerId ORDER BY p.createdAt DESC")
    List<PurchasePerformed> findAll(@Param("playerId") UUID playerId, Pageable pageable);
}
