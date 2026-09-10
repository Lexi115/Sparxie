package io.lexi115.sparxie.game.weapon;

import io.lexi115.sparxie.game.weapon.dto.WeaponDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "inventory", contextId = "weaponClient", url = "${app.http.client-uri.weapon}")
public interface WeaponClient {
    @GetMapping("/{weaponId}")
    WeaponDto getById(@PathVariable String weaponId);
}
