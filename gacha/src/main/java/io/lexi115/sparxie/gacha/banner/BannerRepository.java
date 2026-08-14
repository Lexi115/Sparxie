package io.lexi115.sparxie.gacha.banner;

import java.util.Optional;

public interface BannerRepository {
    Optional<Banner> getById(String id);

    Optional<Banner> getDefaultById(String id);
}
