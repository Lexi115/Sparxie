package io.lexi115.sparxie.game.game;

import io.lexi115.sparxie.game.game.dto.PurchaseRequest;
import io.lexi115.sparxie.game.game.dto.PurchaseResponse;
import io.lexi115.sparxie.game.game.dto.WarpRequest;
import io.lexi115.sparxie.game.game.dto.WarpResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/game")
@RequiredArgsConstructor
public class GameController {
    private final GameService gameService;

    @PostMapping("/warp/pull")
    public WarpResponse performWarp(
            @RequestHeader("X-User-Id") UUID playerId,
            @Valid @RequestBody final WarpRequest request
    ) {
        return gameService.performWarp(playerId, request);
    }

    @PostMapping("/shop/purchase")
    public PurchaseResponse performPurchase(
            @RequestHeader("X-User-Id") UUID playerId,
            @Valid @RequestBody final PurchaseRequest request
    ) {
        return gameService.performPurchase(playerId, request);
    }
}
