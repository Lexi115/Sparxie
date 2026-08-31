package io.lexi115.sparxie.gacha.banner;

import io.lexi115.sparxie.gacha.banner.exception.BannerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BannerService {
    private final BannerTemplateService bannerTemplateService;
    private final BannerRepository bannerRepository;

    @Cacheable("banners")
    public Banner getBanner(final String id) {
        var editsBanner = bannerRepository.findById(id).orElse(null);
        if (editsBanner == null || editsBanner.getType() == null) {
            throw new BannerNotFoundException(id);
        }
        // Load default banner for specific type.
        var defaultBannerId = "default_" + editsBanner.getType().name().toLowerCase();
        var defaultBanner = bannerTemplateService.getById(defaultBannerId);
        return defaultBanner.mergeWith(editsBanner);
    }
}
