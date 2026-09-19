package io.lexi115.sparxie.logger.histories;

import io.lexi115.sparxie.logger.shop.ShopService;
import io.lexi115.sparxie.logger.shop.dto.PurchaseHistory;
import io.lexi115.sparxie.logger.warps.WarpService;
import io.lexi115.sparxie.logger.warps.dto.WarpHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HistoryService {
    private final ShopService shopService;
    private final WarpService warpService;

    public WarpHistory getWarpHistory(final UUID playerId, final String bannerType, final Pageable pageable) {
        return warpService.getHistory(playerId, bannerType.toLowerCase().trim(), pageable);
    }

    public PurchaseHistory getPurchaseHistory(final UUID playerId, final Pageable pageable) {
        return shopService.getHistory(playerId, pageable);
    }
}
