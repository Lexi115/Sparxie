package io.lexi115.sparxie.logger.warps;

import io.lexi115.sparxie.logger.warps.dto.WarpResponse;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "warps_performed")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WarpPerformed {
    @Id
    @Column(updatable = false, nullable = false)
    private UUID transactionId;

    @Column(updatable = false, nullable = false)
    private UUID playerId;

    @Column(updatable = false, nullable = false)
    private String bannerId;

    @Column(updatable = false, nullable = false)
    private String bannerType;

    @Column(updatable = false, nullable = false)
    private Instant createdAt;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(updatable = false, nullable = false, columnDefinition = "jsonb")
    private WarpResponse result;
}
