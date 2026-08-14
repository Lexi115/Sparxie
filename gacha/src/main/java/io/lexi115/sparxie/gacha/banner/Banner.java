package io.lexi115.sparxie.gacha.banner;

import io.lexi115.sparxie.gacha.player.PlayerPity;
import io.lexi115.sparxie.gacha.warp.WarpOutcome;
import io.lexi115.sparxie.gacha.warp.WarpResultItem;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
public class Banner implements Cloneable {
    private String id;
    private String name;
    private BannerType type;
    private BannerCurrency currency;
    private Map<Integer, Integer> costs;

    private Map<StarRarity, List<BannerItem>> winPool;
    private Map<StarRarity, List<BannerItem>> lossPool;
    private Map<StarRarity, NavigableMap<Integer, Double>> rarityRates;
    private Map<StarRarity, Double> winRates;

    public WarpResultItem pull(final PlayerPity playerPity) {
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

    @Override
    public Banner clone() {
        try {
            Banner clone = (Banner) super.clone();

            if (this.costs != null) {
                clone.setCosts(new HashMap<>(this.costs));
            }

            if (this.winPool != null) {
                var clonedWinPool = new HashMap<>(this.winPool);
                clonedWinPool.replaceAll((_, list) -> new ArrayList<>(list));
                clone.setWinPool(clonedWinPool);
            }

            if (this.lossPool != null) {
                var clonedLossPool = new HashMap<>(this.lossPool);
                clonedLossPool.replaceAll((_, list) -> new ArrayList<>(list));
                clone.setLossPool(clonedLossPool);
            }

            if (this.rarityRates != null) {
                var clonedRarityRates = new HashMap<>(this.rarityRates);
                clonedRarityRates.replaceAll((_, map) -> new TreeMap<>(map));
                clone.setRarityRates(clonedRarityRates);
            }

            if (this.winRates != null) {
                clone.setWinRates(new HashMap<>(this.winRates));
            }

            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
