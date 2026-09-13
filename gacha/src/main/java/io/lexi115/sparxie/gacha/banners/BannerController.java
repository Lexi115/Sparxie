package io.lexi115.sparxie.gacha.banners;

import io.lexi115.sparxie.gacha.banners.dto.BannerDetailsDto;
import io.lexi115.sparxie.gacha.banners.dto.BannerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/banners")
@RequiredArgsConstructor
public class BannerController {
    private final BannerService bannerService;
    private final BannerMapper bannerMapper;

    @GetMapping("/{bannerId}")
    public BannerDto getById(@PathVariable final String bannerId) {
        var banner = bannerService.getBanner(bannerId);
        return bannerMapper.toDto(banner);
    }

    @GetMapping("/{bannerId}/details")
    public BannerDetailsDto getDetailsById(@PathVariable final String bannerId) {
        var banner = bannerService.getBanner(bannerId);
        return bannerMapper.toDetailsDto(banner);
    }
}
