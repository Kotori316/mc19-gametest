package com.example.examplemod.gametest;

import java.util.Collection;
import java.util.List;

import com.example.examplemod.ExampleMod;
import net.minecraft.core.BlockPos;
import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestGenerator;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.gametest.framework.TestFunction;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.gametest.GameTestHolder;

@GameTestHolder(value = ExampleMod.MODID)
public final class RegisterViaAnnotation {
    @GameTest
    public void placeDirt(GameTestHelper helper) {
        var pos = BlockPos.ZERO.above();
        helper.setBlock(pos, Blocks.DIRT);
        helper.succeedIf(() -> helper.assertBlockState(pos, b -> b.is(Blocks.DIRT), () -> "Expect DIRT"));
    }

    @GameTest
    public void instance_test1(GameTestHelper helper) {
        helper.succeed();
    }

    @GameTest
    public static void static_test2(GameTestHelper helper) {
        helper.succeed();
    }

    @GameTestGenerator
    public Collection<TestFunction> generator2() {
        return List.of(new TestFunction(
            "defaultBatch", /* batch name */
            "generated_test", /* test name */
            "%s:empty".formatted(ExampleMod.MODID), /* structure name */
            100, /* max ticks */
            0L, /* setup ticks */
            true, /* required */
            g -> {
                g.assertBlock(new BlockPos(0, 1, 0), Blocks.AIR::equals, "");
                g.succeed();
            }
        ));
    }
}
