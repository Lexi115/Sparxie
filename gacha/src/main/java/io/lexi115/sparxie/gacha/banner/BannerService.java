package io.lexi115.sparxie.gacha.banner;

import io.lexi115.sparxie.gacha.cache.Cache;
import org.springframework.stereotype.Service;

import java.util.Arrays;

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
        var bannerTypeString = Arrays.stream(id.split("_")).findFirst().orElse(null);
        if (bannerTypeString == null) {
            return null;
        }
        var cachedTemplate = cache.get(bannerTypeString + "_default");
        if (cachedTemplate == null) {
            return null;
        }
        var edits = bannerRepository.getById(id).orElse(null);
        if (edits == null) {
            return null;
        }

        var merged = bannerMapper.merge(cachedTemplate, edits);
        cache.set(id, merged, config.getCacheForMillis());
        return merged;
    }

    private void init() {
        var defaultCharacterBanner = bannerRepository.getById("character_default").orElseThrow();
        var defaultWeaponBanner = bannerRepository.getById("weapon_default").orElseThrow();
        cache.set(defaultCharacterBanner.getId(), defaultCharacterBanner);
        cache.set(defaultWeaponBanner.getId(), defaultWeaponBanner);
    }

}
