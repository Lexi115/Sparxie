package io.lexi115.sparxie.game.warps.transactions;

import io.lexi115.sparxie.game.warps.dto.WarpResponse;
import jakarta.persistence.*;
import lombok.*;
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

    @Column(nullable = false, updatable = false)
    private String bannerId;

    @Column(nullable = false, updatable = false)
    private String bannerType;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @Setter
    @Enumerated(EnumType.STRING)
    private WarpTransactionStatus status;

    @Setter
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private WarpResponse result;

    public boolean isCompleted() {
        return this.status == WarpTransactionStatus.COMPLETED;
    }
}
