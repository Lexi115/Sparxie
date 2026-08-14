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
        init();
    }

    public Banner getById(final String id) {
        var cachedBanner = cache.get(id);
        if (cachedBanner != null) {
            return cachedBanner;
        }
        var edits = bannerRepository.getById(id).orElse(null);
        if (edits == null || edits.getType() == null) {
            return null;
        }
        var cachedTemplate = cache.get("default_" + edits.getType().name().toLowerCase());
        var merged = cachedTemplate == null ? edits : bannerMapper.merge(cachedTemplate, edits);
        cache.set(id, merged, config.getCacheForMillis());
        return merged;
    }

    private void init() {
        for (var type : BannerType.values()) {
            bannerRepository.getById("default_" + type.name().toLowerCase())
                    .ifPresent(defaultBanner -> cache.set(defaultBanner.getId(), defaultBanner));
        }
    }

}
