package io.lexi115.sparxie.game.game;

import io.lexi115.sparxie.game.shop.dto.PurchaseRequest;
import io.lexi115.sparxie.game.shop.dto.PurchaseResponse;
import io.lexi115.sparxie.game.warps.dto.WarpRequest;
import io.lexi115.sparxie.game.warps.dto.WarpResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/game")
@RequiredArgsConstructor
public class GameController {
    private final GameService gameService;

    @PostMapping("/warps")
    public WarpResponse performWarp(
            @Valid @RequestBody final WarpRequest request,
            @RequestHeader("X-User-Id") UUID playerId
    ) {
        return gameService.performWarp(request, playerId);
    }

    @PostMapping("/purchases")
    public PurchaseResponse performPurchase(
            @RequestHeader("X-User-Id") UUID playerId,
            @Valid @RequestBody final PurchaseRequest request
    ) {
        return gameService.performPurchase(request, playerId);
    }
}
