package io.lexi115.sparxie.gacha.banner.dto;

import io.lexi115.sparxie.gacha.banner.Banner;
import io.lexi115.sparxie.gacha.banner.BannerItem;
import io.lexi115.sparxie.gacha.banner.PulledBannerItem;
import io.lexi115.sparxie.gacha.util.EnumMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.*;

@Mapper(componentModel = "spring", uses = EnumMapper.class)
public interface BannerMapper {

    default Banner merge(final Banner template, final Banner edits) {
        if (template == null || edits == null) {
            return null;
        }
        var merged = template.clone();

        if (edits.getId() != null) {
            merged.setId(edits.getId());
        }
        if (edits.getName() != null) {
            merged.setName(edits.getName());
        }
        if (edits.getType() != null) {
            merged.setType(edits.getType());
        }
        if (edits.getCosts() != null) {
            var cost = Optional.ofNullable(merged.getCosts()).orElse(new HashMap<>());
            cost.putAll(edits.getCosts());
            merged.setCosts(cost);
        }
        if (edits.getWinPool() != null) {
            var winPool = Optional.ofNullable(merged.getWinPool()).orElse(new HashMap<>());
            edits.getWinPool().forEach((rarity, list)
                    -> winPool.put(rarity, new ArrayList<>(list)));
            merged.setWinPool(winPool);
        }
        if (edits.getLossPool() != null) {
            var lossPool = Optional.ofNullable(merged.getLossPool()).orElse(new HashMap<>());
            edits.getLossPool().forEach((rarity, list)
                    -> lossPool.put(rarity, new ArrayList<>(list)));
            merged.setLossPool(lossPool);
        }
        if (edits.getRarityRates() != null) {
            var rarityRates = Optional.ofNullable(merged.getRarityRates()).orElse(new HashMap<>());
            edits.getRarityRates().forEach((rarity, map)
                    -> rarityRates.put(rarity, new TreeMap<>(map)));
            merged.setRarityRates(rarityRates);
        }
        if (edits.getWinRates() != null) {
            var winRates = Optional.ofNullable(merged.getWinRates()).orElse(new HashMap<>());
            winRates.putAll(edits.getWinRates());
            merged.setWinRates(winRates);
        }

        return merged;
    }

    BannerPullResultDto toDto(BannerPullResult original);

    BannerDto toDto(Banner original);

    BannerDetailsDto toDetailsDto(Banner original);

    @Mapping(source = "item", target = "itemId")
    PulledBannerItemDto toDto(PulledBannerItem original);

    List<Long> mapToIds(List<BannerItem> value);

    default Long mapToId(final BannerItem item) {
        return item == null ? null : item.id();
    }
}
