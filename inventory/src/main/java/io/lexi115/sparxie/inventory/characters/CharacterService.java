package io.lexi115.sparxie.inventory.characters;

import io.lexi115.sparxie.inventory.characters.exceptions.CharacterNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterService {
    private final CharacterRepository characterRepository;

    @Cacheable(value = "characters", key = "'char_' + #id")
    public Character getById(final String id) {
        return characterRepository.findById(id)
                .orElseThrow(() -> new CharacterNotFoundException(id));
    }

    @Cacheable(value = "characters", key = "'exists_' + #id")
    public boolean existsById(final String id) {
        return characterRepository.existsById(id);
    }
}
