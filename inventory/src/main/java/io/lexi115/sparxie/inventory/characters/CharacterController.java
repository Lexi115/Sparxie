package io.lexi115.sparxie.inventory.characters;

import io.lexi115.sparxie.inventory.characters.dto.CharacterDto;
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
    public CharacterDto getById(@PathVariable final String characterId) {
        var character = characterService.getById(characterId);
        return new CharacterDto(character.getId(), character.getName(), character.getRarity().getValue());
    }
}
