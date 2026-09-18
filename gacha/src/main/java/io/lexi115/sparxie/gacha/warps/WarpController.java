package io.lexi115.sparxie.gacha.warps;

import io.lexi115.sparxie.gacha.warps.dto.WarpRequest;
import io.lexi115.sparxie.gacha.warps.dto.WarpResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/warps")
@RequiredArgsConstructor
public class WarpController {
    private final WarpService warpService;
    private final WarpMapper warpMapper;

    @PostMapping
    public WarpResponse pull(
            @Valid @RequestBody final WarpRequest request,
            @RequestHeader("X-User-Id") final UUID playerId
    ) {
        var warpResult = warpService.pull(request, playerId);
        return warpMapper.toDto(warpResult);
    }
}
