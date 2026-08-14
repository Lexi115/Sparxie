package io.lexi115.sparxie.gacha.banner;

import io.lexi115.sparxie.gacha.banner.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.*;

@Mapper(componentModel = "spring")
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

    @Mapping(source = "item", target = "itemId")
    PulledBannerItemDto map(PulledBannerItem original);

    BannerDto toDto(Banner original);

    BannerDetailsDto toDetailsDto(Banner original);

    default Long map(final BannerItem original) {
        return original == null ? null : original.id();
    }

    default Integer map(final StarRarity original) {
        return original == null ? null : original.getValue();
    }

    List<Long> map(List<BannerItem> original);
}
