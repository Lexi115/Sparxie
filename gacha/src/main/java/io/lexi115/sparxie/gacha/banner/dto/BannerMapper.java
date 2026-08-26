package io.lexi115.sparxie.gacha.banner.dto;

import io.lexi115.sparxie.gacha.banner.Banner;
import io.lexi115.sparxie.gacha.banner.BannerItem;
import io.lexi115.sparxie.gacha.banner.StarRarity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BannerMapper {


    BannerDto toDto(Banner original);

    BannerDetailsDto toDetailsDto(Banner original);

    default String map(final BannerItem original) {
        return original == null ? null : original.id();
    }

    default Integer map(final StarRarity original) {
        return original == null ? null : original.getValue();
    }

    List<String> map(List<BannerItem> original);
}
