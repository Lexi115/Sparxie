package io.lexi115.sparxie.gacha.banner;

import java.util.Optional;

public interface BannerRepository {
    Optional<Banner> findById(String id);

    Optional<Banner> findDefaultById(String id);
}
