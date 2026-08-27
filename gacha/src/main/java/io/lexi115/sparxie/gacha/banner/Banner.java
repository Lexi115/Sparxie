package io.lexi115.sparxie.gacha.banner;

import io.lexi115.sparxie.gacha.player.PlayerPity;
import io.lexi115.sparxie.gacha.warp.WarpOutcome;
import io.lexi115.sparxie.gacha.warp.WarpResultItem;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
@NoArgsConstructor
public class Banner {
    private String id;
    private String name;
    private BannerType type;
    private BannerCurrency currency;
    private Map<Integer, Long> costs;
    private Map<StarRarity, List<BannerItem>> winPool;
    private Map<StarRarity, List<BannerItem>> lossPool;
    private Map<StarRarity, NavigableMap<Integer, Double>> rarityRates;
    private Map<StarRarity, Double> winRates;

    public Banner(final Banner source) {
        this.id = source.id;
        this.name = source.name;
        this.type = source.type;
        this.currency = source.currency;
        if (source.costs != null) {
            this.setCosts(new HashMap<>(source.costs));
        }
        if (source.winPool != null) {
            var clonedWinPool = new HashMap<>(source.winPool);
            clonedWinPool.replaceAll((_, list) -> new ArrayList<>(list));
            this.setWinPool(clonedWinPool);
        }
        if (source.lossPool != null) {
            var clonedLossPool = new HashMap<>(source.lossPool);
            clonedLossPool.replaceAll((_, list) -> new ArrayList<>(list));
            this.setLossPool(clonedLossPool);
        }
        if (source.rarityRates != null) {
            var clonedRarityRates = new HashMap<>(source.rarityRates);
            clonedRarityRates.replaceAll((_, map) -> new TreeMap<>(map));
            this.setRarityRates(clonedRarityRates);
        }
        if (source.winRates != null) {
            this.setWinRates(new HashMap<>(source.winRates));
        }
    }


    public WarpResultItem pullItem(final PlayerPity playerPity) {
        var fourStarPity = playerPity.getPity(type, StarRarity.FOUR);
        var fiveStarPity = playerPity.getPity(type, StarRarity.FIVE);
        var fourStarRate = getRarityRates(StarRarity.FOUR, fourStarPity);
        var fiveStarRate = getRarityRates(StarRarity.FIVE, fiveStarPity);
        var randomizer = ThreadLocalRandom.current();
        var roll = randomizer.nextDouble();

        var starRarity = (roll < fiveStarRate) ? StarRarity.FIVE
                : (roll < fourStarRate + fiveStarRate) ? StarRarity.FOUR
                : StarRarity.THREE;
        var outcome = decideOutcome(starRarity, playerPity.isGuaranteed(type, starRarity));
        var item = chooseItem(starRarity, outcome);
        return new WarpResultItem(item, outcome);
    }

    private BannerItem chooseItem(StarRarity rarity, WarpOutcome outcome) {
        var randomizer = ThreadLocalRandom.current();
        var chosenList = (rarity == StarRarity.THREE) ? lossPool.get(rarity)
                : (outcome != WarpOutcome.LOSS) ? winPool.get(rarity) : lossPool.get(rarity);
        return chosenList.get(randomizer.nextInt(chosenList.size()));
    }

    private WarpOutcome decideOutcome(StarRarity rarity, boolean guaranteed) {
        var randomizer = ThreadLocalRandom.current();
        return rarity == StarRarity.THREE ? WarpOutcome.LOSS
                : guaranteed ? WarpOutcome.GUARANTEED
                : (randomizer.nextDouble() < winRates.get(rarity)) ? WarpOutcome.WIN
                : WarpOutcome.LOSS;
    }

    private Double getRarityRates(StarRarity rarity, Integer pityValue) {
        var thresholdsMap = rarityRates.get(rarity);
        if (thresholdsMap == null) {
            return 0.0;
        }
        var probability = thresholdsMap.floorEntry(pityValue);
        return probability == null ? 0.0 : probability.getValue();
    }

    public Banner mergeWith(final Banner editBanner) {
        var mergedBanner = new Banner(this);
        if (editBanner == null) {
            return mergedBanner;
        }
        if (editBanner.getId() != null) {
            mergedBanner.setId(editBanner.getId());
        }
        if (editBanner.getName() != null) {
            mergedBanner.setName(editBanner.getName());
        }
        if (editBanner.getType() != null) {
            mergedBanner.setType(editBanner.getType());
        }
        if (editBanner.getCosts() != null) {
            var cost = Optional.ofNullable(mergedBanner.getCosts()).orElse(new HashMap<>());
            cost.putAll(editBanner.getCosts());
            mergedBanner.setCosts(cost);
        }
        if (editBanner.getWinPool() != null) {
            var winPool = Optional.ofNullable(mergedBanner.getWinPool()).orElse(new HashMap<>());
            editBanner.getWinPool().forEach((rarity, list)
                    -> winPool.put(rarity, new ArrayList<>(list)));
            mergedBanner.setWinPool(winPool);
        }
        if (editBanner.getLossPool() != null) {
            var lossPool = Optional.ofNullable(mergedBanner.getLossPool()).orElse(new HashMap<>());
            editBanner.getLossPool().forEach((rarity, list)
                    -> lossPool.put(rarity, new ArrayList<>(list)));
            mergedBanner.setLossPool(lossPool);
        }
        if (editBanner.getRarityRates() != null) {
            var rarityRates = Optional.ofNullable(mergedBanner.getRarityRates()).orElse(new HashMap<>());
            editBanner.getRarityRates().forEach((rarity, map)
                    -> rarityRates.put(rarity, new TreeMap<>(map)));
            mergedBanner.setRarityRates(rarityRates);
        }
        if (editBanner.getWinRates() != null) {
            var winRates = Optional.ofNullable(mergedBanner.getWinRates()).orElse(new HashMap<>());
            winRates.putAll(editBanner.getWinRates());
            mergedBanner.setWinRates(winRates);
        }
        return mergedBanner;
    }
}
