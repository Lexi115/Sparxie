package io.lexi115.sparxie.gacha.warp;

import io.lexi115.sparxie.gacha.warp.dto.WarpMapper;
import io.lexi115.sparxie.gacha.warp.dto.WarpRequest;
import io.lexi115.sparxie.gacha.warp.dto.WarpResultDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/warp")
@RequiredArgsConstructor
public class WarpController {
    private final WarpService warpService;
    private final WarpMapper warpMapper;

    @PostMapping("/warp")
    public WarpResultDto performWarp(@Valid @RequestBody final WarpRequest request) {
        var warpResult = warpService.performWarp(request);
        return warpMapper.toDto(warpResult);
    }
}
