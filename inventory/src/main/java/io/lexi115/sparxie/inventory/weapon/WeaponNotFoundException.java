package io.lexi115.sparxie.inventory.weapon;

public class WeaponNotFoundException extends RuntimeException {
    public WeaponNotFoundException(final String message) {
        super(message);
    }
}
