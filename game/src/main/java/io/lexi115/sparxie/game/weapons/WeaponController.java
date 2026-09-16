package io.lexi115.sparxie.game.weapons;

import io.lexi115.sparxie.game.weapons.dto.Weapon;
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
    public Weapon getById(@PathVariable final String weaponId) {
        return weaponService.getById(weaponId);
    }
}
