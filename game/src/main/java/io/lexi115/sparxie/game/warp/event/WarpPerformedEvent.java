package io.lexi115.sparxie.game.warp.event;

import java.util.List;

public record WarpPerformedEvent(String playerId, List<String> itemIds) {
}
