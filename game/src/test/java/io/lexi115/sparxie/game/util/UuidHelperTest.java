package io.lexi115.sparxie.game.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class UuidHelperTest {

    UuidHelper uuidHelper;

    @BeforeEach
    public void setUp() {
        uuidHelper = new UuidHelper();
    }

    @Test
    public void test() {
        var uuid = UUID.fromString("b87ba24a-b3c7-486b-be9e-63e99408627c");
        var consumeUuid = uuidHelper.generateNameUuid(uuid + "_consume");
        var giveUuid = uuidHelper.generateNameUuid(uuid + "_give");
        Assertions.assertEquals("2f620e1f-268d-3db0-b996-8b15ffeddd57", consumeUuid.toString());
        Assertions.assertEquals("09084730-d07a-31c8-a3f6-2421daf7d3ca", giveUuid.toString());
    }

}
