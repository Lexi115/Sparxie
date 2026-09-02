package io.lexi115.sparxie.gacha.banner.dto;

import io.lexi115.sparxie.gacha.banner.Banner;
import io.lexi115.sparxie.gacha.banner.StarRarity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BannerMapper {
    BannerDto toDto(Banner original);

    BannerDetailsDto toDetailsDto(Banner original);

    default Integer map(final StarRarity original) {
        return original == null ? null : original.getValue();
    }
}
