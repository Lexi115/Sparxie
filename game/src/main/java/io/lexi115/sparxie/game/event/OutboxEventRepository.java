package io.lexi115.sparxie.game.event;

import org.springframework.data.domain.Limit;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface OutboxEventRepository extends CrudRepository<OutboxEvent, UUID> {
    List<OutboxEvent> findAllBy(Limit limit);
}
