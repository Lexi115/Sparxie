package io.lexi115.sparxie.user.messaging;

import java.util.List;

public interface OutboxEventRepository {
    List<OutboxEvent> findTop100ByOrderByCreatedAtAsc();

    void save(OutboxEvent event);

    void delete(OutboxEvent event);
}
