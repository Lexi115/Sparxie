package io.lexi115.sparxie.gacha.warp;

import io.lexi115.sparxie.gacha.banner.BannerItem;
import io.lexi115.sparxie.gacha.banner.StarRarity;
import io.lexi115.sparxie.gacha.warp.dto.WarpResultDto;
import io.lexi115.sparxie.gacha.warp.dto.WarpResultItemDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WarpMapper {
    WarpResultDto toDto(WarpResult original);

    @Mapping(source = "item", target = "itemId")
    WarpResultItemDto map(WarpResultItem original);

    default String map(final BannerItem original) {
        return original == null ? null : original.id();
    }

    default Integer map(final StarRarity original) {
        return original == null ? null : original.getValue();
    }

    List<String> map(List<BannerItem> original);
}
