package io.lexi115.sparxie.inventory.weapon;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeaponService {
    private final WeaponRepository weaponRepository;

    public Weapon getById(final String id) {
        return weaponRepository.findById(id)
                .orElseThrow(() -> new WeaponNotFoundException(id));
    }
}
