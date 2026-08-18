package io.lexi115.sparxie.inventory.character;

import java.util.Optional;

public interface CharacterRepository {
    Optional<Character> findById(String id);
}
