package com.example.examplemod.gametest;

import com.example.examplemod.ExampleMod;
import net.minecraft.core.BlockPos;
import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.gametest.GameTestHolder;
import net.minecraftforge.gametest.PrefixGameTestTemplate;

@GameTestHolder(value = ExampleMod.MODID)
@PrefixGameTestTemplate(value = false)
public final class DispenserTest {
    @GameTest(template = "dispenser_place_water")
    public void push_to_place_water(GameTestHelper helper) {
        final var buttonPos = new BlockPos(2, 3, 3);
        helper.pressButton(buttonPos);
        helper.succeedWhen(() -> helper.assertBlockState(new BlockPos(2, 2, 2), b -> b.is(Blocks.WATER), () -> "Expect Water"));
    }
}
