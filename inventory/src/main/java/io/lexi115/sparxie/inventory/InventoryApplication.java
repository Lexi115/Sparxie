package io.lexi115.sparxie.inventory;

import io.flamingock.api.annotations.EnableFlamingock;
import io.flamingock.api.annotations.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableFlamingock(
        stages = {
                @Stage(location = "io.lexi115.sparxie.inventory.db.flamingock.migrations")
        }
)
@SpringBootApplication
@EnableCaching
public class InventoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryApplication.class, args);
    }

}
