package io.lexi115.sparxie.gacha.players;

import io.lexi115.sparxie.gacha.banners.BannerType;
import io.lexi115.sparxie.gacha.banners.StarRarity;
import io.lexi115.sparxie.gacha.warp.WarpOutcome;
import io.lexi115.sparxie.gacha.warp.WarpResultItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PityTest {

    private Pity pity;

    @BeforeEach
    void setUp() {
        pity = new Pity();
    }

    @Test
    public void testUpdate() {
        var wr = new WarpResultItem("a", StarRarity.THREE, WarpOutcome.LOSS);
        for (int i = 0; i < 20; i++) {
            pity.update(BannerType.STANDARD_EVENT, wr);
        }
        assertEquals(20, pity.getValue(BannerType.STANDARD_EVENT, StarRarity.FOUR));
        assertEquals(20, pity.getValue(BannerType.STANDARD_EVENT, StarRarity.FIVE));
    }
}
