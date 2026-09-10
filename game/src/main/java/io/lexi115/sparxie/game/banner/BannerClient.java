package io.lexi115.sparxie.game.banner;

import io.lexi115.sparxie.game.banner.dto.Banner;
import io.lexi115.sparxie.game.banner.dto.BannerDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "gacha", contextId = "bannerClient", url = "${app.http.client-uri.banner}")
public interface BannerClient {
    @GetMapping("/{id}")
    Banner getById(@PathVariable String id);

    @GetMapping("/details/{id}")
    BannerDetails getDetailsById(@PathVariable String id);
}
