package com.example.examplemod.gametest;

import java.util.concurrent.atomic.AtomicInteger;

import com.example.examplemod.ExampleMod;
import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraftforge.gametest.GameTestHolder;
import net.minecraftforge.gametest.PrefixGameTestTemplate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@GameTestHolder(ExampleMod.MODID)
public final class GameTestParameters {
    private static final Logger LOGGER = LogManager.getLogger();

    @GameTest(attempts = 0, template = "empty")
    public void attempts_0(GameTestHelper helper) {
        helper.succeed();
    }

    @GameTest(templateNamespace = "minecraft")
    public void template_name(GameTestHelper helper) {
        helper.fail("This test should not be called.");
    }

    @GameTest(required = false, template = "empty")
    public void require_false(GameTestHelper helper) {
        LOGGER.info("Optional test 'require_false' is called.");
        helper.fail("This test isn't required.");
    }

    @GameTest(template = "empty")
    @PrefixGameTestTemplate(value = false)
    public void set_template_no_prefix(GameTestHelper helper) {
        // This test requires structure file whose name is "empty.snbt".
        helper.succeed();
    }

    @SuppressWarnings("SpellCheckingInspection")
    @GameTest(template = "empty")
    public void set_template_with_prefix(GameTestHelper helper) {
        // This test requires structure file whose name is "gametestparameters.empty.snbt".
        helper.succeed();
    }

    static final AtomicInteger attemptCounter = new AtomicInteger(0);
    int attemptCounterField = 0;

    @GameTest(attempts = 10, template = "empty", requiredSuccesses = 5)
    public void attempt_instance_field(GameTestHelper helper) {
        LOGGER.info("[attempt_instance_field] Global Counter: {}, Instance Counter: {}",
            attemptCounter.get(), this.attemptCounterField);
        this.attemptCounterField = attemptCounter.get();

        if (attemptCounter.get() % 2 == 0) {
            attemptCounter.getAndIncrement();
            helper.succeed();
        } else {
            helper.fail("Failed as %d is odd.".formatted(attemptCounter.getAndIncrement()));
        }
    }
}
