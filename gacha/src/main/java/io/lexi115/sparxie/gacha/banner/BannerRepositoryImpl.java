package io.lexi115.sparxie.gacha.banner;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Repository;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Repository
public class BannerRepositoryImpl implements BannerRepository {

    private final ResourceLoader resourceLoader;

    @Value("${app.storage.banners-path}")
    private String bannerDirPath;

    public BannerRepositoryImpl(final ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public Optional<Banner> getById(final String id) {
        var prefix = id.startsWith("default_") ? "classpath:data/banners" : "file:" + bannerDirPath;
        try (var stream = resourceLoader.getResource(prefix + "/" + id + ".json").getInputStream()) {
            return Optional.of(loadBanner(stream));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    private Banner loadBanner(final InputStream stream) {
        var objectMapper = new ObjectMapper();
        var rootNode = objectMapper.reader().readTree(stream);
        var banner = new Banner();

        setBannerInfo(banner, rootNode);
        setPools(banner, rootNode);
        setRarityRates(banner, rootNode);
        setWinRates(banner, rootNode);
        setCosts(banner, rootNode);

        return banner;
    }

    private void setBannerInfo(final Banner banner, final JsonNode rootNode) {
        if (rootNode.has("id")) {
            banner.setId(rootNode.get("id").asString());
        }
        if (rootNode.has("name")) {
            banner.setName(rootNode.get("name").asString());
        }
        if (rootNode.has("type")) {
            banner.setType(BannerType.valueOf(rootNode.get("type").asString().toUpperCase()));
        }
        if (rootNode.has("currency")) {
            banner.setCurrency(BannerCurrency.valueOf(rootNode.get("currency").asString().toUpperCase()));
        }
    }

    private void setPools(final Banner banner, final JsonNode rootNode) {
        var winPool = loadPool(rootNode, "winPool");
        var lossPool = loadPool(rootNode, "lossPool");

        if (!winPool.isEmpty()) {
            if (banner.getWinPool() == null) {
                banner.setWinPool(new HashMap<>());
            }
            banner.getWinPool().putAll(winPool);
        }

        if (!lossPool.isEmpty()) {
            if (banner.getLossPool() == null) {
                banner.setLossPool(new HashMap<>());
            }
            banner.getLossPool().putAll(lossPool);
        }
    }

    private void setRarityRates(final Banner banner, final JsonNode rootNode) {
        var rarityRates = loadRarityRates(rootNode);
        if (!rarityRates.isEmpty()) {
            if (banner.getRarityRates() == null) {
                banner.setRarityRates(new HashMap<>());
            }
            banner.getRarityRates().putAll(rarityRates);
        }
    }

    private void setWinRates(final Banner banner, final JsonNode rootNode) {
        var winRates = loadWinRates(rootNode);
        if (!winRates.isEmpty()) {
            if (banner.getWinRates() == null) {
                banner.setWinRates(new HashMap<>());
            }
            banner.getWinRates().putAll(winRates);
        }
    }

    private void setCosts(final Banner banner, final JsonNode rootNode) {
        var costs = loadCosts(rootNode);
        if (!costs.isEmpty()) {
            if (banner.getCosts() == null) {
                banner.setCosts(new HashMap<>());
            }
            banner.getCosts().putAll(costs);
        }
    }

    private Map<StarRarity, List<BannerItem>> loadPool(final JsonNode rootNode, final String poolNameKey) {
        var pool = new HashMap<StarRarity, List<BannerItem>>();
        var poolNode = rootNode.get(poolNameKey);
        if (poolNode != null) {
            for (StarRarity rarity : StarRarity.values()) {
                var rarityNode = poolNode.get(rarity.getValue().toString());
                if (rarityNode != null) {
                    var rarityList = new ArrayList<BannerItem>();
                    for (JsonNode itemNode : rarityNode) {
                        var itemId = itemNode.asLong();
                        var item = new BannerItem(itemId, rarity);
                        rarityList.add(item);
                    }
                    pool.put(rarity, rarityList);
                }
            }
        }
        return pool;
    }

    private Map<StarRarity, NavigableMap<Integer, Double>> loadRarityRates(final JsonNode rootNode) {
        var map = new HashMap<StarRarity, NavigableMap<Integer, Double>>();
        var ratesNode = rootNode.get("rarityRates");
        if (ratesNode != null) {
            for (StarRarity rarity : StarRarity.values()) {
                var rarityNode = ratesNode.get(rarity.getValue().toString());
                if (rarityNode != null) {
                    var rarityMap = new TreeMap<Integer, Double>();
                    rarityNode.forEachEntry((thresholdKey, rateNode)
                            -> rarityMap.put(Integer.parseInt(thresholdKey), rateNode.doubleValue(0)));
                    map.put(rarity, rarityMap);
                }
            }
        }
        return map;
    }

    private Map<StarRarity, Double> loadWinRates(final JsonNode rootNode) {
        var map = new HashMap<StarRarity, Double>();
        var ratesNode = rootNode.get("winRates");
        if (ratesNode != null) {
            for (StarRarity rarity : StarRarity.values()) {
                var rarityNode = ratesNode.get(rarity.getValue().toString());
                if (rarityNode != null) {
                    map.put(rarity, rarityNode.doubleValue());
                }
            }
        }
        return map;
    }

    private Map<Integer, Integer> loadCosts(final JsonNode rootNode) {
        var map = new HashMap<Integer, Integer>();
        var costsNode = rootNode.get("costs");
        if (costsNode != null) {
            costsNode.forEachEntry((pullAmount, costNode)
                    -> map.put(Integer.parseInt(pullAmount), costNode.intValue(0)));
        }
        return map;
    }
}
