package com.example.examplemod.gametest;

import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.example.examplemod.ExampleMod;
import net.minecraft.core.BlockPos;
import net.minecraft.gametest.framework.GameTestGenerator;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.gametest.framework.TestFunction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.gametest.GameTestHolder;
import net.minecraftforge.registries.ForgeRegistries;

@GameTestHolder(value = ExampleMod.MODID)
public final class RegisterDynamicTest {
    @GameTestGenerator
    public Collection<TestFunction> generator() {
        return Stream.of(Blocks.WATER, Blocks.LAVA).flatMap(b ->
            IntStream.range(0, 2).boxed().map(i ->
                new TestFunction(
                    "defaultBatch", /* batch name */
                    "%s_flow_%d".formatted(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(b)).getPath(), i), /* test name */
                    "%s:%s.Generator".formatted(ExampleMod.MODID, getClass().getSimpleName()).toLowerCase(Locale.ROOT), /* structure name */
                    100, /* max ticks */
                    40L, /* setup ticks */
                    true, /* required */
                    g -> actualTest(g, b, i)
                )
            )
        ).toList();
    }

    private void actualTest(GameTestHelper helper, Block fluidBlock, int param) {
        var pos = new BlockPos(2, 2 + param, 2);
        helper.startSequence()
            .thenExecute(() -> helper.setBlock(pos, fluidBlock))
            .thenIdle(40)
            .thenExecute(() -> helper.assertBlockPresent(fluidBlock, new BlockPos(2, 2, 2)))
            .thenSucceed();
    }

    @GameTestGenerator
    public Collection<TestFunction> generator2(){
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
