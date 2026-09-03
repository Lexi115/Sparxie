package io.lexi115.sparxie.gacha.warp.dto;

import io.lexi115.sparxie.gacha.banner.StarRarity;
import io.lexi115.sparxie.gacha.warp.WarpOutcome;
import io.lexi115.sparxie.gacha.warp.WarpResult;
import org.mapstruct.EnumMapping;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WarpMapper {

    @EnumMapping(nameTransformationStrategy = "case", configuration = "lower")
    String toStringOutcome(WarpOutcome outcome);
    
    WarpResultDto toDto(WarpResult original);

    default Integer map(final StarRarity original) {
        return original == null ? null : original.getValue();
    }
}
