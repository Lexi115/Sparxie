package io.lexi115.sparxie.inventory.weapons;

import io.lexi115.sparxie.inventory.weapons.dto.WeaponDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weapons")
@RequiredArgsConstructor
public class WeaponController {
    private final WeaponService weaponService;

    @GetMapping("/{weaponId}")
    public WeaponDto getById(@PathVariable final String weaponId) {
        var weapon = weaponService.getById(weaponId);
        return new WeaponDto(weapon.getId(), weapon.getName(), weapon.getRarity().getValue());
    }
}
