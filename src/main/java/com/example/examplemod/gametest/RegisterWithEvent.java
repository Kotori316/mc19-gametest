package com.example.examplemod.gametest;

import com.example.examplemod.ExampleMod;
import net.minecraft.core.BlockPos;
import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.RegisterGameTestsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ExampleMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class RegisterWithEvent {
    @SubscribeEvent
    public static void registerTest(RegisterGameTestsEvent event) throws ReflectiveOperationException {
        event.register(RegisterWithEvent.class.getMethod("placeDirt", GameTestHelper.class));
    }

    @GameTest(templateNamespace = ExampleMod.MODID)
    public static void placeDirt(GameTestHelper helper) {
        var pos = BlockPos.ZERO.above();
        helper.setBlock(pos, Blocks.DIRT);
        helper.succeedIf(() -> helper.assertBlockState(pos, b -> b.is(Blocks.DIRT), () -> "Expect DIRT"));
    }
}
