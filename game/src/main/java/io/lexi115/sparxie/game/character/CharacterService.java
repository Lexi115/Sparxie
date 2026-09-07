package io.lexi115.sparxie.game.character;

import io.lexi115.sparxie.game.character.dto.CharacterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterService {
    private final CharacterClient characterClient;

    public CharacterDto getById(final String characterId) {
        return characterClient.getById(characterId);
    }
}
