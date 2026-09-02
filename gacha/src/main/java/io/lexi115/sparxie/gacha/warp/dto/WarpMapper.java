package io.lexi115.sparxie.gacha.warp.dto;

import io.lexi115.sparxie.gacha.banner.StarRarity;
import io.lexi115.sparxie.gacha.warp.WarpResult;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WarpMapper {
    WarpResultDto toDto(WarpResult original);

    default Integer map(final StarRarity original) {
        return original == null ? null : original.getValue();
    }
}
