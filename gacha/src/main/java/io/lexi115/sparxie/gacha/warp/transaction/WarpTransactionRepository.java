package io.lexi115.sparxie.gacha.warp.transaction;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface WarpTransactionRepository extends CrudRepository<WarpTransaction, UUID> {
}
