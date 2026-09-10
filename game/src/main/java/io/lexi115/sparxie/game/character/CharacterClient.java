package io.lexi115.sparxie.game.character;

import io.lexi115.sparxie.game.character.dto.CharacterDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "inventory", contextId = "characterClient", url = "${app.http.client-uri.character}")
public interface CharacterClient {
    @GetMapping("/{characterId}")
    CharacterDto getById(@PathVariable String characterId);
}
