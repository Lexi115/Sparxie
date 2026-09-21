package io.lexi115.sparxie.logger.users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "users_created")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreated {
    @Id
    @Column(updatable = false, nullable = false)
    private UUID id;

    @Column(updatable = false, nullable = false)
    private UUID userId;

    @Column(updatable = false)
    private String username;

    @Column(updatable = false, nullable = false)
    private Instant createdAt;

    @Column(updatable = false, nullable = false)
    private String provider;
}
