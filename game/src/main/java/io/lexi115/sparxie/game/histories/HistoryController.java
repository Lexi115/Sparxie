package io.lexi115.sparxie.game.histories;

import io.lexi115.sparxie.game.histories.dto.PurchaseHistory;
import io.lexi115.sparxie.game.histories.dto.WarpHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/histories")
@RequiredArgsConstructor
public class HistoryController {
    private final HistoryService historyService;

    @GetMapping("/warps/{bannerType}")
    public WarpHistory getWarpHistory(
            @RequestHeader("X-User-Id") final UUID playerId,
            @PathVariable final String bannerType,
            final Pageable pageable
    ) {
        return historyService.getWarpHistory(playerId, bannerType, pageable);
    }

    @GetMapping("/purchases")
    public PurchaseHistory getPurchaseHistory(
            @RequestHeader("X-User-Id") final UUID playerId,
            final Pageable pageable
    ) {
        return historyService.getPurchaseHistory(playerId, pageable);
    }
}
