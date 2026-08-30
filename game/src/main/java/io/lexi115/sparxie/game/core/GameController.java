package io.lexi115.sparxie.game.core;

import io.lexi115.sparxie.game.warp.dto.WarpRequest;
import io.lexi115.sparxie.game.warp.dto.WarpResultDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
@RequiredArgsConstructor
public class GameController {
    private final GameService gameService;

    @PostMapping("/warp/pull")
    public WarpResultDto performWarp(@Valid @RequestBody final WarpRequest request) {
        return gameService.performWarp(request);
    }

    @PostMapping("/shop/purchase")
    public void performPurchase(@Valid @RequestBody final PurchaseRequest request) {
        gameService.performPurchase(request);
    }
}
