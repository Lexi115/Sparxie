package io.lexi115.sparxie.game.warp;

import io.lexi115.sparxie.game.warp.dto.GachaWarpRequest;
import io.lexi115.sparxie.game.warp.dto.GachaWarpResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "gacha", contextId = "warpClient", url = "${app.http.client-uri.warp}")
public interface WarpClient {
    @PostMapping("/pull")
    GachaWarpResponse performWarp(GachaWarpRequest request);
}
