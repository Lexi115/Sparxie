package io.lexi115.sparxie.gacha.system;

import io.lexi115.sparxie.gacha.banner.dto.BannerPullResult;

public record GachaTransaction(Long id, BannerPullResult results) {
}
