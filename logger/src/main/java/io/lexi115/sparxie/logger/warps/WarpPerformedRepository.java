package io.lexi115.sparxie.logger.warps;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WarpPerformedRepository extends JpaRepository<WarpPerformed, UUID> {
}
