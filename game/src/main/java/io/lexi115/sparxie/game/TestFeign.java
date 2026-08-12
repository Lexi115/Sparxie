package io.lexi115.sparxie.game;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "test", url = "http://gacha:8080")
public interface TestFeign {
    @GetMapping("/test")
    String test();
}
