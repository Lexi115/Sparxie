package io.lexi115.sparxie.inventory.player;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/players")
@RequiredArgsConstructor
@Validated
public class PlayerController {
    private final PlayerService playerService;

    @GetMapping("/{playerId}/characters")
    public Map<String, Long> getCharacters(
            @PathVariable final UUID playerId,
            final Pageable pageable
    ) {
        return playerService.getCharacters(playerId, pageable);
    }

    @GetMapping("/{playerId}/weapons")
    public Map<String, Long> getWeapons(
            @PathVariable final UUID playerId,
            final Pageable pageable
    ) {
        return playerService.getWeapons(playerId, pageable);
    }

    @GetMapping("/{playerId}/materials")
    public Map<String, Long> getMaterials(
            @PathVariable final UUID playerId,
            final Pageable pageable
    ) {
        return playerService.getMaterials(playerId, pageable);
    }
}
