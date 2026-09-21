package io.lexi115.sparxie.logger.histories;

import io.lexi115.sparxie.logger.shop.dto.PurchaseHistory;
import io.lexi115.sparxie.logger.warps.dto.WarpHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/histories")
@RequiredArgsConstructor
public class HistoryController {
    private final HistoryService historyService;

    @GetMapping("/warps/{playerId}/{bannerType}")
    public WarpHistory getWarpHistory(
            @PathVariable final UUID playerId,
            @PathVariable final String bannerType,
            final Pageable pageable
    ) {
        return historyService.getWarpHistory(playerId, bannerType, pageable);
    }

    @GetMapping("/purchases/{playerId}")
    public PurchaseHistory getPurchaseHistory(@PathVariable final UUID playerId, final Pageable pageable) {
        return historyService.getPurchaseHistory(playerId, pageable);
    }
}
