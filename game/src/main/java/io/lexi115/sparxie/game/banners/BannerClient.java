package io.lexi115.sparxie.game.banners;

import io.lexi115.sparxie.game.banners.dto.Banner;
import io.lexi115.sparxie.game.banners.dto.BannerDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "gacha",
        contextId = "bannerClient",
        url = "${app.http.client-uri.banners}",
        path = "/banners"
)
public interface BannerClient {
    @GetMapping("/{bannerId}")
    Banner getById(@PathVariable String bannerId);

    @GetMapping("/{bannerId}/details")
    BannerDetails getDetailsById(@PathVariable String bannerId);
}
