package io.lexi115.sparxie.logger.warps;

import io.lexi115.sparxie.logger.warps.dto.WarpHistory;
import io.lexi115.sparxie.logger.warps.dto.WarpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WarpService {
    private final WarpPerformedRepository warpPerformedRepository;
    private final WarpMapper warpMapper;

    public WarpHistory getHistory(final UUID playerId, final String bannerType, final Pageable pageable) {
        var historyList = warpPerformedRepository.findAll(playerId, bannerType, pageable);
        return warpMapper.toHistory(historyList, playerId, bannerType);
    }

    public void createWarpPerformed(
            final UUID transactionId,
            final UUID playerId,
            final String bannerId,
            final String bannerType,
            final Instant createdAt,
            final WarpResponse result
    ) {
        var warpPerformed = WarpPerformed.builder()
                .transactionId(transactionId)
                .playerId(playerId)
                .bannerId(bannerId)
                .bannerType(bannerType.toLowerCase())
                .createdAt(createdAt)
                .result(result)
                .build();
        warpPerformedRepository.save(warpPerformed);
    }
}
