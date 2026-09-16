package io.lexi115.sparxie.game.warp;

import io.lexi115.sparxie.game.game.dto.WarpRequest;
import io.lexi115.sparxie.game.game.dto.WarpResponse;
import io.lexi115.sparxie.game.warp.events.WarpEventService;
import io.lexi115.sparxie.game.warp.transactions.WarpTransaction;
import io.lexi115.sparxie.game.warp.transactions.WarpTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WarpService {
    private final WarpClient warpClient;
    private final WarpTransactionService warpTransactionService;
    private final WarpEventService warpEventService;
    private final WarpMapper warpMapper;

    public WarpResponse performWarp(final WarpRequest request, final UUID playerId) {
        var gachaRequest = warpMapper.toGachaRequest(request);
        var gachaResponse = warpClient.pull(gachaRequest, playerId);
        return warpMapper.toClientResponse(gachaResponse);
    }

    public WarpTransaction getTransaction(final UUID transactionId, final UUID playerId) {
        var oldTransaction = warpTransactionService.getById(transactionId);
        if (oldTransaction != null) {
            if (!playerId.equals(oldTransaction.getPlayerId())) {
                throw new IllegalArgumentException("Transaction not owned!");
            }
            return oldTransaction;
        }
        return null;
    }

    public WarpTransaction createTransaction(final UUID transactionId, final UUID playerId) {
        return warpTransactionService.create(transactionId, playerId);
    }

    public void commitTransaction(final WarpTransaction transaction) {
        warpTransactionService.commit(transaction);
        warpEventService.warpPerformed(transaction);
    }
}
