package io.lexi115.sparxie.game.warp.dto;

import io.lexi115.sparxie.game.game.dto.WarpRequest;
import io.lexi115.sparxie.game.game.dto.WarpResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface WarpMapper {

    @Mapping(target = "playerId", source = "playerId")
    GachaWarpRequest toGachaRequest(UUID playerId, WarpRequest request);

    WarpResponse toClientResponse(GachaWarpResponse response);
}
