package io.lexi115.sparxie.gacha.players;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface PlayerRepository extends MongoRepository<Player, UUID> {
}
