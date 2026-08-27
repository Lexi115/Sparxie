package io.lexi115.sparxie.inventory.weapon;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeaponService {
    private final WeaponRepository weaponRepository;

    @Cacheable(value = "weapons", key = "'lc_' + #id")
    public Weapon getById(final String id) {
        return weaponRepository.findById(id)
                .orElseThrow(() -> new WeaponNotFoundException(id));
    }

    @Cacheable(value = "weapons", key = "'exists_' + #id")
    public boolean existsById(final String id) {
        return weaponRepository.existsById(id);
    }
}
