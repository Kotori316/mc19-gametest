package com.example.examplemod.gametest;

import com.example.examplemod.ExampleMod;
import net.minecraft.gametest.framework.AfterBatch;
import net.minecraft.gametest.framework.BeforeBatch;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.gametest.GameTestHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@GameTestHolder(value = ExampleMod.MODID)
public final class BeforeAfterBatch {
    private static final Logger LOGGER = LogManager.getLogger();

    @BeforeBatch(batch = "defaultBatch")
    public static void before(ServerLevel level) {
        LOGGER.info("BeforeBatch is called with level {}", level.getSeed());
    }

    @AfterBatch(batch = "defaultBatch")
    public static void after(ServerLevel level) {
        LOGGER.info("AfterBatch is called with level {}", level.getSeed());
    }
}
