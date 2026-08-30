package io.lexi115.sparxie.inventory.inventory;

import io.lexi115.sparxie.inventory.character.CharacterService;
import io.lexi115.sparxie.inventory.concurrent.Lock;
import io.lexi115.sparxie.inventory.inventory.exception.InventoryLockedException;
import io.lexi115.sparxie.inventory.inventory.transaction.InventoryTransactionService;
import io.lexi115.sparxie.inventory.player.Player;
import io.lexi115.sparxie.inventory.player.PlayerService;
import io.lexi115.sparxie.inventory.util.UuidHelper;
import io.lexi115.sparxie.inventory.weapon.WeaponService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final PlayerService playerService;
    private final CharacterService characterService;
    private final WeaponService weaponService;
    private final InventoryTransactionService inventoryTransactionService;
    private final Lock playerLock;
    private final UuidHelper uuidHelper;

    public void giveItems(final UUID transactionId, final UUID playerId, final Map<String, Long> items) {
        var actionUuid = uuidHelper.generateNameUuid(transactionId + "_give");
        executeTransaction(actionUuid, playerId, player ->
                items.forEach((itemId, amount) ->
                        withValidItem(itemId, amount, (realItemId, itemType) -> player.giveItem(realItemId, itemType, amount))
                ));
    }

    public void consumeItems(final UUID transactionId, final UUID playerId, final Map<String, Long> items) {
        var actionUuid = uuidHelper.generateNameUuid(transactionId + "_consume");
        executeTransaction(actionUuid, playerId, player ->
                items.forEach((itemId, amount) ->
                        withValidItem(itemId, amount, (realItemId, itemType) -> player.consumeItem(realItemId, itemType, amount))
                ));
    }

    // @Transactional
    public void executeTransaction(final UUID transactionId, final UUID playerId, final Consumer<Player> action) {
        System.out.println("INVENTORY TRANSACTION ID: " + transactionId);
        var lockName = "inventory_lock_" + playerId;
        if (!playerLock.acquire(lockName)) {
            throw new InventoryLockedException("Please wait a bit before using the inventory again!");
        }
        try {
            var transaction = inventoryTransactionService.getOrCreateTransaction(transactionId, playerId);
            if (transaction.isCompleted()) {
                return;
            }
            var player = playerService.getById(playerId);
            action.accept(player);
            playerService.savePlayer(player);
            printPlayerInventory(player); // TODO debug
            inventoryTransactionService.commitTransaction(transaction);
        } finally {
            playerLock.release(lockName);
        }
    }

    private void withValidItem(final String itemId, final Long amount, BiConsumer<String, ItemType> action) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be 0 or less");
        }
        var realItemId = itemId.toLowerCase().trim();
        var itemType = switch (realItemId.split("_")[0]) {
            case "char" -> ItemType.CHARACTER;
            case "lc" -> ItemType.WEAPON;
            default -> ItemType.MATERIAL;
        };
        //todo rimetti quando ci saranno tutti ID validi salvati
//        if (!itemExists(realItemId, itemType)) {
//            throw new IllegalArgumentException("Invalid ID");
//        }
        action.accept(realItemId, itemType);
    }

    private boolean itemExists(final String itemId, final ItemType itemType) {
        return switch (itemType) {
            case CHARACTER -> characterService.existsById(itemId);
            case WEAPON -> weaponService.existsById(itemId);
            case MATERIAL -> true;
        };
    }

    private void printPlayerInventory(Player player) {
        System.out.println("---- CHARACTERS ----");
        player.getCharacters().forEach((id, amount) -> System.out.println(id + "(" + amount + ")"));
        System.out.println("---- WEAPONS ----");
        player.getWeapons().forEach((id, amount) -> System.out.println(id + "(" + amount + ")"));
        System.out.println("---- MATERIALS ----");
        player.getMaterials().forEach((id, amount) -> System.out.println(id + "(" + amount + ")"));
    }
}
