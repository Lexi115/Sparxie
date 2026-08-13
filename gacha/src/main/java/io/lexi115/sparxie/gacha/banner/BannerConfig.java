package io.lexi115.sparxie.gacha.banner;

import lombok.Getter;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class BannerConfig {
    private final long cacheForMillis = 5000;
}
