package io.lexi115.sparxie.gacha.banner;

public class BannerNotFoundException extends RuntimeException {

    public BannerNotFoundException(final String bannerId) {
        super("Banner with ID '" + bannerId + "' not found.");
    }
}
