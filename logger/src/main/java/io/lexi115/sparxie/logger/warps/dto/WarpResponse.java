package io.lexi115.sparxie.logger.warps.dto;

import java.util.List;

public record WarpResponse(
        List<WarpItem> items
) {
}
