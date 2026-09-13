package io.lexi115.sparxie.game.characters;

import io.lexi115.sparxie.game.characters.dto.Character;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/characters")
@RequiredArgsConstructor
public class CharacterController {
    private final CharacterService characterService;

    @GetMapping("/{characterId}")
    public Character getById(@PathVariable final String characterId) {
        return characterService.getById(characterId);
    }
}
