package io.lexi115.sparxie.user.event;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface OutboxEventRepository extends CrudRepository<OutboxEvent, UUID> {
}
