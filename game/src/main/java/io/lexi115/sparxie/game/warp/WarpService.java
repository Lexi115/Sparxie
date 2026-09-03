package io.lexi115.sparxie.game.warp;

import io.lexi115.sparxie.game.game.dto.WarpRequest;
import io.lexi115.sparxie.game.game.dto.WarpResponse;
import io.lexi115.sparxie.game.warp.dto.WarpMapper;
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
    private final WarpMapper warpMapper;

    public WarpResponse performWarp(final UUID playerId, final WarpRequest request) {
        var gachaRequest = warpMapper.toGachaRequest(playerId, request);
        var gachaResponse = warpClient.performWarp(gachaRequest);
        return warpMapper.toClientResponse(gachaResponse);
    }

    public WarpTransaction startTransaction(final UUID transactionId, final UUID playerId) {
        return warpTransactionService.getOrCreateById(transactionId, playerId);
    }

    public void commitTransaction(final WarpTransaction transaction) {
        warpTransactionService.commit(transaction);
    }
}
