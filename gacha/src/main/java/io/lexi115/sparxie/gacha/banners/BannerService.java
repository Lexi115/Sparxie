package io.lexi115.sparxie.gacha.banners;

import io.lexi115.sparxie.gacha.banners.exceptions.BannerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BannerService {
    private final BannerRepository bannerRepository;
    private final BannerTemplateService bannerTemplateService;

    @Cacheable("banners")
    public Banner getBanner(final String id) {
        var banner = bannerRepository.findById(id)
                .orElseThrow(() -> new BannerNotFoundException(id));
        if (banner.isTemplate() || banner.getType() == null) {
            throw new BannerNotFoundException(id);
        }
        var defaultBannerId = "default_" + banner.getType().name().toLowerCase();
        var defaultBanner = bannerTemplateService.getById(defaultBannerId);
        return defaultBanner.mergeWith(banner);
    }
}
