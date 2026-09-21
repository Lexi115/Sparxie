package io.lexi115.sparxie.game.histories;

import io.lexi115.sparxie.game.histories.dto.PurchaseHistory;
import io.lexi115.sparxie.game.histories.dto.WarpHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HistoryService {
    private final HistoryClient historyClient;

    public WarpHistory getWarpHistory(final UUID playerId, final String bannerType, final Pageable pageable) {
        return historyClient.getWarpHistory(playerId, bannerType, pageable);
    }

    public PurchaseHistory getPurchaseHistory(final UUID playerId, final Pageable pageable) {
        return historyClient.getPurchaseHistory(playerId, pageable);
    }
}
