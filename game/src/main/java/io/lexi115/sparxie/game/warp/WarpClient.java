package io.lexi115.sparxie.game.warp;

import io.lexi115.sparxie.game.warp.dto.WarpRequest;
import io.lexi115.sparxie.game.warp.dto.WarpResultDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "gacha", contextId = "warpClient", url = "${app.http.warp-client-uri}")
public interface WarpClient {
    @PostMapping("/pull")
    WarpResultDto pull(WarpRequest request);
}
