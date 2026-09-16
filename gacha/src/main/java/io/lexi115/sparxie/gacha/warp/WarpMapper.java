package io.lexi115.sparxie.gacha.warp;

import io.lexi115.sparxie.gacha.banners.StarRarity;
import io.lexi115.sparxie.gacha.warp.dto.WarpResponse;
import org.mapstruct.EnumMapping;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WarpMapper {

    @EnumMapping(nameTransformationStrategy = "case", configuration = "lower")
    String toStringOutcome(WarpOutcome outcome);

    WarpResponse toDto(WarpResult original);

    default Integer map(final StarRarity original) {
        return original == null ? null : original.getValue();
    }
}
