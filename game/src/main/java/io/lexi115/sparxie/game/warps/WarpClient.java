package io.lexi115.sparxie.game.warps;

import io.lexi115.sparxie.game.warps.dto.GachaWarpRequest;
import io.lexi115.sparxie.game.warps.dto.GachaWarpResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.UUID;

@FeignClient(
        name = "gacha",
        contextId = "warpClient",
        url = "${app.http.client-uri.warp}",
        path = "/warps"
)
public interface WarpClient {
    @PostMapping
    GachaWarpResponse pull(GachaWarpRequest request, @RequestHeader("X-User-Id") UUID playerId);
}
