package io.lexi115.sparxie.game.shop.transaction;

import io.lexi115.sparxie.game.shop.dto.PurchaseResponse;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "shop_transactions")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShopTransaction {
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
    private ShopTransactionStatus status;

    @Setter
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private PurchaseResponse result;

    public boolean isCompleted() {
        return this.status == ShopTransactionStatus.COMPLETED;
    }
}
