package io.lexi115.sparxie.game.warp;

import io.lexi115.sparxie.game.warp.dto.WarpRequest;
import io.lexi115.sparxie.game.warp.dto.WarpResultDto;
import org.springframework.stereotype.Service;

@Service
public class WarpService {
    private final WarpClient warpClient;

    public WarpService(final WarpClient warpClient) {
        this.warpClient = warpClient;
    }

    public WarpResultDto pull(final WarpRequest request) {
        return warpClient.pull(request);
    }
}
