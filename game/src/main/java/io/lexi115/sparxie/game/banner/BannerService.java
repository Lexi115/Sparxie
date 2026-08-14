package io.lexi115.sparxie.game.banner;

import org.springframework.stereotype.Service;

@Service
public class BannerService {
    private final BannerClient bannerClient;

    public BannerService(final BannerClient bannerClient) {
        this.bannerClient = bannerClient;
    }

    public BannerDetailsDto getDetailsById(final String id) {
        return bannerClient.getDetailsById(id);
    }
}
