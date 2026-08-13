package io.lexi115.sparxie.game.banner;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "gacha", contextId = "bannerClient", url = "http://gacha:8080/banner")
public interface BannerClient {
    @GetMapping("/details/{id}")
    BannerDetailsDto getDetailsById(@PathVariable String id);
}
