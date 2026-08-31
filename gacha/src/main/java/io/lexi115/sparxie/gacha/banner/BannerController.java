package io.lexi115.sparxie.gacha.banner;

import io.lexi115.sparxie.gacha.banner.dto.BannerDetailsDto;
import io.lexi115.sparxie.gacha.banner.dto.BannerDto;
import io.lexi115.sparxie.gacha.banner.dto.BannerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/banner")
@RequiredArgsConstructor
public class BannerController {
    private final BannerService bannerService;
    private final BannerMapper bannerMapper;

    @GetMapping("/{bannerId}")
    public BannerDto getById(@PathVariable final String bannerId) {
        var banner = bannerService.getBanner(bannerId);
        return bannerMapper.toDto(banner);
    }

    @GetMapping("/details/{bannerId}")
    public BannerDetailsDto getDetailsById(@PathVariable final String bannerId) {
        var banner = bannerService.getBanner(bannerId);
        return bannerMapper.toDetailsDto(banner);
    }
}
