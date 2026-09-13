package io.lexi115.sparxie.game.characters;

import io.lexi115.sparxie.game.characters.dto.Character;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "inventory",
        contextId = "characterClient",
        url = "${app.http.client-uri.characters}",
        path = "/characters"
)
public interface CharacterClient {
    @GetMapping("/{characterId}")
    Character getById(@PathVariable String characterId);
}
