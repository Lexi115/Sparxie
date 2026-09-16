package io.lexi115.sparxie.gacha.banners.exceptions;

public class BannerNotFoundException extends RuntimeException {

    public BannerNotFoundException(final String bannerId) {
        super("Banner with ID '" + bannerId + "' not found.");
    }
}
