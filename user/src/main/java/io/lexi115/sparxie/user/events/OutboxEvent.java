package io.lexi115.sparxie.user.events;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "outbox_events")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OutboxEvent {
    @Id
    private UUID id;

    @Column(nullable = false)
    private String key;

    @Column(nullable = false)
    private String topic;

    @Column(nullable = false)
    private String payload;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private EventType eventType;
}
