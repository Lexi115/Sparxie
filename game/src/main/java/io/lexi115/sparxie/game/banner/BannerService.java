package io.lexi115.sparxie.game.banner;

import io.lexi115.sparxie.game.banner.dto.BannerDetailsDto;
import io.lexi115.sparxie.game.banner.dto.BannerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BannerService {
    private final BannerClient bannerClient;

    public BannerDto getById(final String id) {
        return bannerClient.getById(id);
    }

    public BannerDetailsDto getDetailsById(final String id) {
        return bannerClient.getDetailsById(id);
    }
}
