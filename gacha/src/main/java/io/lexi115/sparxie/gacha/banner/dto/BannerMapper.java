package io.lexi115.sparxie.gacha.banner.dto;

import io.lexi115.sparxie.gacha.banner.Banner;
import io.lexi115.sparxie.gacha.banner.BannerCurrency;
import io.lexi115.sparxie.gacha.banner.BannerType;
import io.lexi115.sparxie.gacha.banner.StarRarity;
import org.mapstruct.EnumMapping;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BannerMapper {

    @EnumMapping(nameTransformationStrategy = "case", configuration = "lower")
    String toStringType(BannerType type);

    @EnumMapping(nameTransformationStrategy = "case", configuration = "lower")
    String toStringCurrency(BannerCurrency currency);

    BannerDto toDto(Banner original);

    BannerDetailsDto toDetailsDto(Banner original);

    default Integer map(final StarRarity original) {
        return original == null ? null : original.getValue();
    }
}
