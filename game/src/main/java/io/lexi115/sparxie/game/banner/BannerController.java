package io.lexi115.sparxie.game.banner;

import io.lexi115.sparxie.game.banner.dto.BannerDetailsDto;
import io.lexi115.sparxie.game.banner.dto.BannerDto;
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

    @GetMapping("/{bannerId}")
    public BannerDto getById(@PathVariable final String bannerId) {
        return bannerService.getById(bannerId);
    }

    @GetMapping("/details/{bannerId}")
    public BannerDetailsDto getDetailsById(@PathVariable final String bannerId) {
        return bannerService.getDetailsById(bannerId);
    }
}
