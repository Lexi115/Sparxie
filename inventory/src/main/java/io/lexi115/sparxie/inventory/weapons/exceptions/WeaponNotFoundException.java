package io.lexi115.sparxie.inventory.weapons.exceptions;

public class WeaponNotFoundException extends RuntimeException {
    public WeaponNotFoundException(final String weaponId) {
        super("Weapon with ID '" + weaponId + "' not found.");
    }
}
