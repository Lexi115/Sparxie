package io.lexi115.sparxie.game.warp.transactions;

import io.lexi115.sparxie.game.game.dto.WarpResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "warp_transactions")
@Getter
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
