package io.lexi115.sparxie.gacha.warp;

import io.lexi115.sparxie.gacha.warp.dto.WarpRequest;
import io.lexi115.sparxie.gacha.warp.dto.WarpResultDto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/warp")
public class WarpController {
    private final WarpService warpService;
    private final WarpMapper warpMapper;

    public WarpController(final WarpService warpService, final WarpMapper warpMapper) {
        this.warpService = warpService;
        this.warpMapper = warpMapper;
    }

    @PostMapping("/pull")
    public WarpResultDto pull(@Valid @RequestBody final WarpRequest request) {
        var result = warpService.pull(request);
        return warpMapper.toDto(result);
    }
}
