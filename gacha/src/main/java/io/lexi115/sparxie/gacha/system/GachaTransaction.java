package io.lexi115.sparxie.gacha.system;

import io.lexi115.sparxie.gacha.banner.BannerPullResult;

import java.util.List;

public record GachaTransaction(Long id, List<BannerPullResult> results) {
}
