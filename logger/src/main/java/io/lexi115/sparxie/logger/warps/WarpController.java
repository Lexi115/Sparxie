package io.lexi115.sparxie.logger.warps;

import io.lexi115.sparxie.logger.warps.dto.WarpHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/warps")
@RequiredArgsConstructor
public class WarpController {
    private final WarpService warpService;

    @GetMapping("/{playerId}/{bannerType}")
    public WarpHistory getHistory(
            @PathVariable final UUID playerId,
            @PathVariable final String bannerType,
            final Pageable pageable
    ) {
        return warpService.getHistory(playerId, bannerType.toLowerCase().trim(), pageable);
    }
}
