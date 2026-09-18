package io.lexi115.sparxie.logger.warps;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface WarpPerformedRepository extends JpaRepository<WarpPerformed, UUID> {
    @Query("SELECT w FROM WarpPerformed w WHERE w.playerId = :playerId AND w.bannerType = :bannerType ORDER BY w.createdAt DESC")
    List<WarpPerformed> findAll(
            @Param("playerId") UUID playerId,
            @Param("bannerType") String bannerType,
            Pageable pageable
    );
}
