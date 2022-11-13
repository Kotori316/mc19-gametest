package com.example.examplemod.gametest;

import com.example.examplemod.ExampleMod;
import net.minecraft.core.BlockPos;
import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.gametest.GameTestHolder;

@GameTestHolder(value = ExampleMod.MODID)
public final class RegisterViaAnnotation {
    @GameTest
    public void placeDirt(GameTestHelper helper) {
        var pos = BlockPos.ZERO.above();
        helper.setBlock(pos, Blocks.DIRT);
        helper.succeedIf(() -> helper.getBlockState(pos).is(Blocks.DIRT));
    }
}
