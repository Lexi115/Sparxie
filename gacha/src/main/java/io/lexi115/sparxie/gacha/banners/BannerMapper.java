package io.lexi115.sparxie.gacha.banners;

import io.lexi115.sparxie.gacha.banners.dto.BannerDetailsDto;
import io.lexi115.sparxie.gacha.banners.dto.BannerDto;
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
