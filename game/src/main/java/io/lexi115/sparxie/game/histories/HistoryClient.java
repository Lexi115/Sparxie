package io.lexi115.sparxie.game.histories;

import io.lexi115.sparxie.game.histories.dto.PurchaseHistory;
import io.lexi115.sparxie.game.histories.dto.WarpHistory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(
        name = "logger",
        contextId = "historyClient",
        url = "${app.http.client-uri.histories}",
        path = "/histories"
)
public interface HistoryClient {
    @GetMapping("/warps/{playerId}/{bannerType}")
    WarpHistory getWarpHistory(@PathVariable UUID playerId, @PathVariable String bannerType, Pageable pageable);

    @GetMapping("/purchases/{playerId}")
    PurchaseHistory getPurchaseHistory(@PathVariable UUID playerId, Pageable pageable);
}
