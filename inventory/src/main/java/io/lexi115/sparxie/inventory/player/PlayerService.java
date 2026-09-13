package io.lexi115.sparxie.inventory.player;

import io.lexi115.sparxie.inventory.inventory.ItemType;
import io.lexi115.sparxie.inventory.player.exception.PlayerNotFoundException;
import io.lexi115.sparxie.inventory.util.CollectionHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final CollectionHelper collectionHelper;

    public Player getById(final UUID playerId) {
        return playerRepository.findById(playerId)
                .orElseThrow(() -> new PlayerNotFoundException(playerId));
    }

    public boolean existsById(final UUID playerId) {
        return playerRepository.existsById(playerId);
    }

    @Transactional
    public void create(final UUID playerId) {
        var player = new Player(playerId);
        playerRepository.save(player);
    }

    public void save(final Player player) {
        playerRepository.save(player);
    }

    @Transactional
    public void deleteById(final UUID playerId) {
        var player = getById(playerId);
        playerRepository.delete(player);
    }

    public Map<String, Long> getCharacters(final UUID playerId, final Pageable pageable) {
        return getItems(playerId, ItemType.CHARACTER, pageable);
    }

    public Map<String, Long> getWeapons(final UUID playerId, final Pageable pageable) {
        return getItems(playerId, ItemType.WEAPON, pageable);
    }

    public Map<String, Long> getMaterials(final UUID playerId, final Pageable pageable) {
        return getItems(playerId, ItemType.MATERIAL, pageable);
    }

    private Map<String, Long> getItems(final UUID playerId, final ItemType itemType, final Pageable pageable) {
        var pageIndex = pageable.getPageNumber();
        if (pageIndex < 0) {
            throw new IllegalArgumentException("Page index must be at least 0");
        }
        var pageSize = pageable.getPageSize();
        if (pageSize <= 0) {
            throw new IllegalArgumentException("Page size must be at least 1");
        }
        var player = getById(playerId);
        var itemMap = switch (itemType) {
            case CHARACTER -> player.getCharacters();
            case WEAPON -> player.getWeapons();
            case MATERIAL -> player.getMaterials();
        };
        var startIndex = pageIndex * pageSize;
        var endIndex = startIndex + (pageSize - 1);
        return collectionHelper.subMap(itemMap, startIndex, endIndex);
    }
}
