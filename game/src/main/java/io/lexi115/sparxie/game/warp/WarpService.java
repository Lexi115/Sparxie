package io.lexi115.sparxie.game.warp;

import io.lexi115.sparxie.game.warp.dto.WarpRequest;
import io.lexi115.sparxie.game.warp.dto.WarpResultDto;
import io.lexi115.sparxie.game.warp.transaction.WarpTransaction;
import io.lexi115.sparxie.game.warp.transaction.WarpTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WarpService {
    private final WarpClient warpClient;
    private final WarpTransactionService warpTransactionService;

    public WarpResultDto performWarp(final WarpRequest request) {
        return warpClient.performWarp(request);
    }

    public WarpTransaction startTransaction(final UUID transactionId, final UUID playerId) {
        return warpTransactionService.getOrCreateById(transactionId, playerId);
    }

    public void commitTransaction(final WarpTransaction transaction) {
        warpTransactionService.commit(transaction);
    }
}
