package io.lexi115.sparxie.inventory.inventories;

import io.lexi115.sparxie.inventory.characters.CharacterService;
import io.lexi115.sparxie.inventory.concurrent.Lock;
import io.lexi115.sparxie.inventory.inventories.exceptions.InventoryLockedException;
import io.lexi115.sparxie.inventory.inventories.transactions.InventoryTransactionService;
import io.lexi115.sparxie.inventory.materials.MaterialService;
import io.lexi115.sparxie.inventory.players.Player;
import io.lexi115.sparxie.inventory.players.PlayerService;
import io.lexi115.sparxie.inventory.util.CollectionHelper;
import io.lexi115.sparxie.inventory.util.UuidHelper;
import io.lexi115.sparxie.inventory.weapons.WeaponService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final PlayerService playerService;
    private final CharacterService characterService;
    private final WeaponService weaponService;
    private final MaterialService materialService;
    private final InventoryTransactionService inventoryTransactionService;
    private final Lock playerLock;
    private final UuidHelper uuidHelper;
    private final CollectionHelper collectionHelper;

    @Transactional
    public void giveItems(final UUID transactionId, final UUID playerId, final Map<String, Long> items) {
        var actionUuid = uuidHelper.generateNameUuid(transactionId + "_give");
        executeTransaction(actionUuid, playerId, player ->
                items.forEach((itemId, amount) ->
                        withValidItem(itemId, amount, (realItemId, itemType) -> player.giveItem(realItemId, itemType, amount))
                ));
    }

    @Transactional
    public void consumeItems(final UUID transactionId, final UUID playerId, final Map<String, Long> items) {
        var actionUuid = uuidHelper.generateNameUuid(transactionId + "_consume");
        executeTransaction(actionUuid, playerId, player ->
                items.forEach((itemId, amount) ->
                        withValidItem(itemId, amount, (realItemId, itemType) -> player.consumeItem(realItemId, itemType, amount))
                ));
    }

    @Transactional
    public void executeTransaction(final UUID transactionId, final UUID playerId, final Consumer<Player> action) {
        var lockName = "inventory_lock_" + playerId;
        if (!playerLock.acquire(lockName)) {
            throw new InventoryLockedException("Please wait a bit before using the inventory again!");
        }
        try {
            var player = playerService.getById(playerId);
            var oldTransaction = inventoryTransactionService.getById(transactionId);
            if (oldTransaction != null) {
                return;
            }
            action.accept(player);
            playerService.save(player);
            inventoryTransactionService.create(transactionId, playerId);
        } finally {
            playerLock.release(lockName);
        }
    }

    private void withValidItem(final String itemId, final Long amount, final BiConsumer<String, ItemType> action) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be 0 or less");
        }
        var realItemId = itemId.toLowerCase().trim();
        var itemType = switch (realItemId.split("_")[0]) {
            case "char" -> ItemType.CHARACTER;
            case "lc" -> ItemType.WEAPON;
            default -> ItemType.MATERIAL;
        };
        if (!itemExists(realItemId, itemType)) {
            throw new IllegalArgumentException("Invalid ID: " + itemId);
        }
        action.accept(realItemId, itemType);
    }

    private boolean itemExists(final String itemId, final ItemType itemType) {
        return switch (itemType) {
            case CHARACTER -> characterService.existsById(itemId);
            case WEAPON -> weaponService.existsById(itemId);
            case MATERIAL -> materialService.existsById(itemId);
        };
    }

    public Map<String, Long> getCharacters(final UUID playerId, final Pageable pageable) {
        return getItems(playerId, ItemType.CHARACTER, pageable, null);
    }

    public Map<String, Long> getWeapons(final UUID playerId, final Pageable pageable) {
        return getItems(playerId, ItemType.WEAPON, pageable, null);
    }

    public Map<String, Long> getMaterials(
            final UUID playerId,
            final Pageable pageable,
            final Predicate<String> idFilter
    ) {
        return getItems(playerId, ItemType.MATERIAL, pageable, idFilter);
    }

    private Map<String, Long> getItems(
            final UUID playerId,
            final ItemType itemType,
            final Pageable pageable,
            final Predicate<String> idFilter
    ) {
        var pageIndex = pageable.getPageNumber();
        if (pageIndex < 0) {
            throw new IllegalArgumentException("Page index must be at least 0");
        }
        var pageSize = pageable.getPageSize();
        if (pageSize <= 0) {
            throw new IllegalArgumentException("Page size must be at least 1");
        }
        var player = playerService.getById(playerId);
        var itemMap = switch (itemType) {
            case CHARACTER -> player.getCharacters();
            case WEAPON -> player.getWeapons();
            case MATERIAL -> player.getMaterials();
        };
        if (idFilter != null) {
            itemMap = collectionHelper.filterMapKeys(itemMap, idFilter);
        }
        var startIndex = pageIndex * pageSize;
        var endIndex = startIndex + (pageSize - 1);
        return collectionHelper.subMap(itemMap, startIndex, endIndex);
    }
}
