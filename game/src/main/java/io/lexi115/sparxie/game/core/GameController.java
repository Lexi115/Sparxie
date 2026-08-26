package io.lexi115.sparxie.game.core;

import io.lexi115.sparxie.game.warp.dto.WarpRequest;
import io.lexi115.sparxie.game.warp.dto.WarpResultDto;
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

    @PostMapping("/pull")
    public WarpResultDto performWarp(@Valid @RequestBody final WarpRequest request) {
        return gameService.performWarp(request);
    }

    @PostMapping("/purchase")
    public void performPurchase(@Valid @RequestBody final PurchaseRequest request) {
        gameService.performPurchase(request);
    }
}
