package io.lexi115.sparxie.gacha.banner;

import io.lexi115.sparxie.gacha.banner.exception.BannerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BannerTemplateService {
    private final BannerRepository bannerRepository;

    @Cacheable("banner_templates")
    public Banner getById(final String id) {
        return bannerRepository.findDefaultById(id)
                .orElseThrow(() -> new BannerNotFoundException(id));
    }
}
