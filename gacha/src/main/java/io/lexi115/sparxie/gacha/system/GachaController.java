package io.lexi115.sparxie.gacha.system;

import io.lexi115.sparxie.gacha.banner.dto.BannerMapper;
import io.lexi115.sparxie.gacha.banner.dto.BannerPullRequest;
import io.lexi115.sparxie.gacha.banner.dto.BannerPullResultDto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gacha")
public class GachaController {
    private final GachaService gachaService;
    private final BannerMapper bannerMapper;

    public GachaController(final GachaService gachaService, final BannerMapper bannerMapper) {
        this.gachaService = gachaService;
        this.bannerMapper = bannerMapper;
    }

    @PostMapping("/pull")
    public BannerPullResultDto pull(@Valid @RequestBody final BannerPullRequest request) {
        var result = gachaService.pull(request);
        return bannerMapper.toDto(result);
    }
}
