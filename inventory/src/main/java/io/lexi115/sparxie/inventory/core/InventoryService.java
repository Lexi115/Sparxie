package io.lexi115.sparxie.inventory.core;

import io.lexi115.sparxie.inventory.cache.Lock;
import io.lexi115.sparxie.inventory.character.CharacterService;
import io.lexi115.sparxie.inventory.core.transaction.InventoryTransactionService;
import io.lexi115.sparxie.inventory.player.NumberOfCopiesExceededException;
import io.lexi115.sparxie.inventory.player.Player;
import io.lexi115.sparxie.inventory.player.PlayerService;
import io.lexi115.sparxie.inventory.weapon.WeaponService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final PlayerService playerService;
    private final CharacterService characterService;
    private final WeaponService weaponService;
    private final InventoryTransactionService inventoryTransactionService;
    private final Lock playerLock;

    // @Transactional
    public void addItems(final UUID transactionId, final UUID playerId, final Map<String, Long> items) {
        var lockName = "inventory_lock_" + playerId;
        if (!playerLock.acquire(lockName)) {
            throw new RuntimeException("lock");
        }
        try {
            var transaction = inventoryTransactionService.getOrCreateTransaction(transactionId, playerId);
            if (transaction.isCompleted()) {
                System.out.println("transaction already completed!");
                return;
            }
            var player = playerService.getById(playerId);
            items.forEach((itemId, amount) -> giveItem(player, itemId, amount));
            inventoryTransactionService.commitTransaction(transaction);
            System.out.println("---- CHARS ----");
            player.getCharacters().forEach((id, am) -> System.out.println(id + " (" + am + ")"));
            System.out.println("---- WEAPONS ----");
            player.getWeapons().forEach((id, am) -> System.out.println(id + " (" + am + ")"));
            System.out.println("---- MATERIALS ----");
            player.getMaterials().forEach((id, am) -> System.out.println(id + " (" + am + ")"));
            System.out.println("---- ----");
            System.out.println("transaction committed: " + transactionId);
        } finally {
            playerLock.release(lockName);
        }
    }

    private void giveItem(final Player player, final String itemId, final Long amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be 0 or less");
        }
        switch (itemId.split("_")[0]) {
            case "char":
                try {
                    var character = characterService.getById(itemId);
                    System.out.println("char found: " + itemId);
                    player.giveItem(character.id(), ItemType.CHARACTER, amount);
                } catch (NumberOfCopiesExceededException e) { // TODO handle errors!!!!!
                    System.out.println("copies exceeded! " + itemId);
                } catch (Exception e) {
                    System.out.println("char not found: " + itemId);
                }
                break;
            case "lc":
                try {
                    var weapon = weaponService.getById(itemId);
                    System.out.println("weapon found: " + itemId);
                    player.giveItem(weapon.id(), ItemType.WEAPON, amount);
                } catch (Exception e) {
                    System.out.println("weapon not found: " + itemId);
                }
                break;
            default:
                try {
                    player.giveItem(itemId, ItemType.MATERIAL, amount);
                } catch (Exception e) {
                    System.out.println("material not found: " + itemId);
                }
        }
    }
}
