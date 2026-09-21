package io.lexi115.sparxie.game.warps;

import io.lexi115.sparxie.game.warps.dto.GachaWarpRequest;
import io.lexi115.sparxie.game.warps.dto.GachaWarpResponse;
import io.lexi115.sparxie.game.warps.dto.WarpRequest;
import io.lexi115.sparxie.game.warps.dto.WarpResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WarpMapper {

    GachaWarpRequest toGachaRequest(WarpRequest request);

    WarpResponse toClientResponse(GachaWarpResponse response);
}
