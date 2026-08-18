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

import java.util.List;
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
    public void addItems(final UUID transactionId, final UUID playerId, final List<String> itemIds) {
        var lockName = "inventory_lock_" + playerId;
        if (!playerLock.acquire(lockName)) {
            throw new RuntimeException("lock");
        }
        try {
            var transaction = inventoryTransactionService.getOrCreateTransaction(transactionId, playerId);
            if (transaction.isCompleted()) {
                return;
            }
            var player = playerService.getById(playerId);
            itemIds.forEach(itemId -> giveItem(player, itemId));
            inventoryTransactionService.commitTransaction(transaction);
        } finally {
            playerLock.release(lockName);
        }
    }

    private void giveItem(final Player player, final String itemId) {
        switch (itemId.split("_")[0]) {
            case "char":
                var character = characterService.getById(itemId);
                try {
                    player.giveCharacter(character);
                } catch (NumberOfCopiesExceededException e) {
                    System.out.println("copies exceeded! " + itemId);
                }
                break;
            case "lc":
                var weapon = weaponService.getById(itemId);
                player.giveWeapon(weapon);
                break;
            default:
                break;
        }
    }
}
