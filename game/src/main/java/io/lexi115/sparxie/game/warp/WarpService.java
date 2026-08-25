package io.lexi115.sparxie.game.warp;

import io.lexi115.sparxie.game.warp.dto.WarpRequest;
import io.lexi115.sparxie.game.warp.dto.WarpResultDto;
import io.lexi115.sparxie.game.warp.transaction.WarpTransaction;
import io.lexi115.sparxie.game.warp.transaction.WarpTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WarpService {
    private final WarpClient warpClient;
    private final WarpTransactionService warpTransactionService;

    public WarpResultDto pull(final WarpRequest request) {
        return warpClient.pull(request);
    }

    public WarpTransaction startTransaction(final UUID transactionId, final UUID playerId) {
        return warpTransactionService.getOrCreateTransaction(transactionId, playerId);
    }

    public void commitTransaction(final WarpTransaction transaction, final Map<String, Long> items) {
        warpTransactionService.commitTransaction(transaction, items);
    }
}
