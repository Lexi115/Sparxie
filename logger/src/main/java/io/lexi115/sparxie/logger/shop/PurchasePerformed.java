package io.lexi115.sparxie.logger.shop;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "purchases_performed")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchasePerformed {
    @Id
    @Column(updatable = false, nullable = false)
    private UUID transactionId;

    @Column(updatable = false, nullable = false)
    private UUID playerId;

    @Column(updatable = false, nullable = false)
    private Instant createdAt;

    @Column(updatable = false, nullable = false)
    private String currency;

    @Column(updatable = false)
    private BigDecimal price;

    @Column(updatable = false, nullable = false)
    private String itemId;

    @Column(updatable = false, nullable = false)
    private Long amount;
}
