package io.lexi115.sparxie.user.events;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface OutboxEventRepository extends CrudRepository<OutboxEvent, UUID> {
}
