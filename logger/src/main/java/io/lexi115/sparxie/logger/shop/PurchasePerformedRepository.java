package io.lexi115.sparxie.logger.shop;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PurchasePerformedRepository extends JpaRepository<PurchasePerformed, UUID> {
}
