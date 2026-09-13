package io.lexi115.sparxie.game.characters;

import io.lexi115.sparxie.game.characters.dto.Character;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterService {
    private final CharacterClient characterClient;

    public Character getById(final String characterId) {
        return characterClient.getById(characterId);
    }
}
