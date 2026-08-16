package io.lexi115.sparxie.game.banner;

import io.lexi115.sparxie.game.banner.dto.BannerDetailsDto;
import io.lexi115.sparxie.game.banner.dto.BannerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// TODO fare error decoders
@FeignClient(name = "gacha", contextId = "bannerClient", url = "${app.http.banner-client-uri}")
public interface BannerClient {
    @GetMapping("/{id}")
    BannerDto getById(@PathVariable String id);

    @GetMapping("/details/{id}")
    BannerDetailsDto getDetailsById(@PathVariable String id);
}
