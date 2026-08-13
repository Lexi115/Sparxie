package io.lexi115.sparxie.gacha.system;

import io.lexi115.sparxie.gacha.banner.BannerPullRequest;
import io.lexi115.sparxie.gacha.banner.BannerPullResult;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/gacha")
public class GachaController {
    private final GachaService gachaService;

    public GachaController(final GachaService gachaService) {
        this.gachaService = gachaService;
    }

    @PostMapping("/pull")
    public List<BannerPullResult> pull(@Valid @RequestBody final BannerPullRequest request) {
        System.out.println(request);
        return gachaService.pull(request);
    }
}
