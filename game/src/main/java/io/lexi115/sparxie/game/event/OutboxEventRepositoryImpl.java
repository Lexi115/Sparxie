package io.lexi115.sparxie.game.event;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class OutboxEventRepositoryImpl implements OutboxEventRepository {

    private final Map<UUID, OutboxEvent> map = new HashMap<>();

    @Override
    public List<OutboxEvent> findTop100ByOrderByCreatedAtAsc() {
        var list = new ArrayList<OutboxEvent>();
        map.forEach((_, e) -> list.add(e));
        return list;
    }

    @Override
    public void save(final OutboxEvent event) {
        map.put(event.id(), event);
    }

    @Override
    public void delete(final OutboxEvent event) {
        map.remove(event.id());
    }
}
