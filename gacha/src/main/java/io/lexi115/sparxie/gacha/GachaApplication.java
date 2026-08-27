package io.lexi115.sparxie.gacha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class GachaApplication {

    static void main(String[] args) {
        SpringApplication.run(GachaApplication.class, args);
    }

}
