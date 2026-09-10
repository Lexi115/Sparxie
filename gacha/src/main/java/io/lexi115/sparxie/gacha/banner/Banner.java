package io.lexi115.sparxie.gacha.banner;

import io.lexi115.sparxie.gacha.player.pity.Pity;
import io.lexi115.sparxie.gacha.warp.WarpOutcome;
import io.lexi115.sparxie.gacha.warp.WarpResultItem;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Document(collection = "banners")
@Getter
@Setter
@NoArgsConstructor
public class Banner {
    @Id
    private String id;

    private String name;

    private BannerType type;

    private BannerCurrency currency;

    private boolean template;

    private String groupId;

    private Map<Integer, Long> costs;

    private Map<StarRarity, List<String>> winPool;

    private Map<StarRarity, List<String>> lossPool;

    private Map<StarRarity, NavigableMap<Integer, Double>> rarityRates;

    private Map<StarRarity, Double> winRates;

    public Banner(final Banner source) {
        this.id = source.id;
        this.name = source.name;
        this.type = source.type;
        this.currency = source.currency;
        this.template = source.template;
        this.groupId = source.groupId;

        if (source.costs != null) {
            this.costs = new HashMap<>(source.costs);
        }

        if (source.winPool != null) {
            var clonedWinPool = new HashMap<>(source.winPool);
            clonedWinPool.replaceAll((_, list) -> new ArrayList<>(list));
            this.winPool = clonedWinPool;
        }

        if (source.lossPool != null) {
            var clonedLossPool = new HashMap<>(source.lossPool);
            clonedLossPool.replaceAll((_, list) -> new ArrayList<>(list));
            this.lossPool = clonedLossPool;
        }

        if (source.rarityRates != null) {
            var clonedRarityRates = new HashMap<>(source.rarityRates);
            clonedRarityRates.replaceAll((_, map) -> new TreeMap<>(map));
            this.rarityRates = clonedRarityRates;
        }

        if (source.winRates != null) {
            this.winRates = new HashMap<>(source.winRates);
        }
    }

    public WarpResultItem pullItem(final Pity pity) {
        var fourStarPity = pity.getValue(type, StarRarity.FOUR);
        var fiveStarPity = pity.getValue(type, StarRarity.FIVE);
        var fourStarRate = getRarityRates(StarRarity.FOUR, fourStarPity);
        var fiveStarRate = getRarityRates(StarRarity.FIVE, fiveStarPity);
        var randomizer = ThreadLocalRandom.current();
        var roll = randomizer.nextDouble();

        var starRarity = (roll < fiveStarRate) ? StarRarity.FIVE
                : (roll < fourStarRate + fiveStarRate) ? StarRarity.FOUR
                : StarRarity.THREE;
        var outcome = decideOutcome(starRarity, pity.isGuaranteed(type, starRarity));
        var itemId = chooseItem(starRarity, outcome);
        return new WarpResultItem(itemId, starRarity, outcome);
    }

    private String chooseItem(StarRarity rarity, WarpOutcome outcome) {
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

        if (editBanner.id != null) {
            mergedBanner.id = editBanner.id;
        }
        if (editBanner.name != null) {
            mergedBanner.name = editBanner.name;
        }
        if (editBanner.type != null) {
            mergedBanner.type = editBanner.type;
        }

        mergedBanner.template = editBanner.template;

        if (editBanner.groupId != null) {
            mergedBanner.groupId = editBanner.groupId;
        }

        if (editBanner.costs != null) {
            var costs = Optional.ofNullable(mergedBanner.costs).orElse(new HashMap<>());
            costs.putAll(editBanner.costs);
            mergedBanner.costs = costs;
        }

        if (editBanner.winPool != null) {
            var winPool = Optional.ofNullable(mergedBanner.winPool).orElse(new HashMap<>());
            editBanner.winPool.forEach((rarity, list) ->
                    winPool.put(rarity, new ArrayList<>(list)));
            mergedBanner.winPool = winPool;
        }

        if (editBanner.lossPool != null) {
            var lossPool = Optional.ofNullable(mergedBanner.lossPool).orElse(new HashMap<>());
            editBanner.lossPool.forEach((rarity, list) ->
                    lossPool.put(rarity, new ArrayList<>(list)));
            mergedBanner.lossPool = lossPool;
        }

        if (editBanner.rarityRates != null) {
            var rarityRates = Optional.ofNullable(mergedBanner.rarityRates).orElse(new HashMap<>());
            editBanner.rarityRates.forEach((rarity, map) ->
                    rarityRates.put(rarity, new TreeMap<>(map)));
            mergedBanner.rarityRates = rarityRates;
        }

        if (editBanner.winRates != null) {
            var winRates = Optional.ofNullable(mergedBanner.winRates).orElse(new HashMap<>());
            winRates.putAll(editBanner.winRates);
            mergedBanner.winRates = winRates;
        }

        return mergedBanner;
    }
}
