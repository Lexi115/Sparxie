package io.lexi115.sparxie.game.system;

import io.lexi115.sparxie.game.banner.BannerPullRequest;
import io.lexi115.sparxie.game.banner.BannerPullResultDto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
public class GameController {
    private final GameService gameService;

    public GameController(final GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/performWarp")
    public BannerPullResultDto performWarp(@Valid @RequestBody final BannerPullRequest request) {
        return gameService.performWarp(request);
    }
}
