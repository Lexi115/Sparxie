package io.lexi115.sparxie.game.warps.transactions;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface WarpTransactionRepository extends CrudRepository<WarpTransaction, UUID> {
}
