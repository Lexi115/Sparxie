package io.lexi115.sparxie.game.weapons;

import io.lexi115.sparxie.game.weapons.dto.Weapon;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "inventory",
        contextId = "weaponClient",
        url = "${app.http.client-uri.weapons}",
        path = "/weapons"
)
public interface WeaponClient {
    @GetMapping("/{weaponId}")
    Weapon getById(@PathVariable String weaponId);
}
