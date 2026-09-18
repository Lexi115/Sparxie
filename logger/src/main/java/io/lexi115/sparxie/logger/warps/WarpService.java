package io.lexi115.sparxie.logger.warps;

import io.lexi115.sparxie.logger.warps.dto.WarpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WarpService {
    private final WarpPerformedRepository warpPerformedRepository;

    public void createWarpPerformed(
            final UUID transactionId,
            final UUID playerId,
            final Instant createdAt,
            final WarpResponse result
    ) {
        var warpPerformed = WarpPerformed.builder()
                .transactionId(transactionId)
                .playerId(playerId)
                .createdAt(createdAt)
                .result(result)
                .build();
        warpPerformedRepository.save(warpPerformed);
    }
}
