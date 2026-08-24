package io.lexi115.sparxie.inventory.character;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class CharacterRepositoryImpl implements CharacterRepository {

    private final Map<String, Character> map = new HashMap<>(Map.of(
            "char_sparkle", new Character("char_sparkle", "Sparkle")
    ));

    @Override
    public Optional<Character> findById(final String id) {
        return Optional.ofNullable(map.get(id));
    }

    @Override
    public boolean existsById(String id) {
        return map.containsKey(id);
    }
}
