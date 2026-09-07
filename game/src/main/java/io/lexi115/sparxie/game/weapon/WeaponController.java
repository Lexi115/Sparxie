package io.lexi115.sparxie.game.weapon;

import io.lexi115.sparxie.game.weapon.dto.WeaponDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weapon")
@RequiredArgsConstructor
public class WeaponController {
    private final WeaponService weaponService;

    @GetMapping("/{weaponId}")
    public WeaponDto getById(@PathVariable final String weaponId) {
        return weaponService.getById(weaponId);
    }
}
