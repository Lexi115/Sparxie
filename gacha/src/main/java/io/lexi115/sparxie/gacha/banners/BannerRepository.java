package io.lexi115.sparxie.gacha.banners;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BannerRepository extends CrudRepository<Banner, String> {
    @Query("{ '_id':  ?0, 'template':  true }")
    Optional<Banner> findTemplateById(String id);
}
