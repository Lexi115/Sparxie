package io.lexi115.sparxie.gacha.warp.transaction;

import io.lexi115.sparxie.gacha.warp.WarpResult;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "warp_transactions")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WarpTransaction {
    @Id
    @Column(nullable = false, updatable = false)
    private UUID transactionId;

    @Column(nullable = false, updatable = false)
    private UUID playerId;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb", nullable = false, updatable = false)
    private WarpResult result;
}
