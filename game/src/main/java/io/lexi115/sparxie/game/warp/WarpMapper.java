package io.lexi115.sparxie.game.warp;

import io.lexi115.sparxie.game.game.dto.WarpRequest;
import io.lexi115.sparxie.game.game.dto.WarpResponse;
import io.lexi115.sparxie.game.warp.dto.GachaWarpRequest;
import io.lexi115.sparxie.game.warp.dto.GachaWarpResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WarpMapper {

    GachaWarpRequest toGachaRequest(WarpRequest request);

    WarpResponse toClientResponse(GachaWarpResponse response);
}
