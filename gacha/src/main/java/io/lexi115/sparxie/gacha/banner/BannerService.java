package io.lexi115.sparxie.gacha.banner;

import io.lexi115.sparxie.gacha.cache.Cache;
import org.springframework.stereotype.Service;

@Service
public class BannerService {

    private final BannerRepository bannerRepository;
    private final BannerMapper bannerMapper;
    private final Cache<Banner> cache;
    private final BannerConfig config;

    public BannerService(
            final BannerRepository bannerRepository,
            final BannerMapper bannerMapper,
            final Cache<Banner> cache,
            final BannerConfig config
    ) {
        this.bannerRepository = bannerRepository;
        this.bannerMapper = bannerMapper;
        this.cache = cache;
        this.config = config;
    }

    public Banner getById(final String id) {
        var cachedBanner = cache.get(id).orElse(null);
        if (cachedBanner != null) {
            return cachedBanner;
        }
        var edits = bannerRepository.getById(id).orElse(null);
        if (edits == null || edits.getType() == null) {
            return null;
        }

        // Load default banner for specific type.
        var defaultBannerId = "default_" + edits.getType().name().toLowerCase();
        var cachedTemplate = cache.get(defaultBannerId).orElse(getDefaultById(defaultBannerId));

        var merged = cachedTemplate == null ? edits : bannerMapper.merge(cachedTemplate, edits);
        cache.set(id, merged, config.getCacheForMillis());
        return merged;
    }

    private Banner getDefaultById(final String id) {
        var defaultBanner = bannerRepository.getDefaultById(id).orElse(null);
        if (defaultBanner == null) {
            return null;
        }
        cache.set(defaultBanner.getId(), defaultBanner);
        return defaultBanner;
    }
}
