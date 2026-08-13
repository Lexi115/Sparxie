package io.lexi115.sparxie.game.system;

import io.lexi115.sparxie.game.banner.BannerPullRequest;
import io.lexi115.sparxie.game.banner.BannerPullResultDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "gacha", contextId = "gachaClient", url = "http://gacha:8080/gacha")
public interface GachaClient {
    @PostMapping("/pull")
    BannerPullResultDto pull(BannerPullRequest request);
}
