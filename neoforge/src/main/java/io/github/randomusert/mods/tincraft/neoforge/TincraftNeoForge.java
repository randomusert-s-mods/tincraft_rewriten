package io.github.randomusert.mods.tincraft.neoforge;

import io.github.randomusert.mods.tincraft.Tincraft;
import net.neoforged.fml.common.Mod;

@Mod(Tincraft.MOD_ID)
public final class TincraftNeoForge {
    public TincraftNeoForge() {
        // Run our common setup.
        Tincraft.LOGGER.info("Neoforge Init started");

        Tincraft.init();

        Tincraft.LOGGER.info("Neoforge Init completed");
    }
}
