package io.lexi115.sparxie.game.banners;

import io.lexi115.sparxie.game.banners.dto.Banner;
import io.lexi115.sparxie.game.banners.dto.BannerDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BannerService {
    private final BannerClient bannerClient;

    public Banner getById(final String id) {
        return bannerClient.getById(id);
    }

    public BannerDetails getDetailsById(final String id) {
        return bannerClient.getDetailsById(id);
    }
}
