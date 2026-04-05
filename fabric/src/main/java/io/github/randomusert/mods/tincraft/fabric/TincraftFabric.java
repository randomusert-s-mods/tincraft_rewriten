package io.github.randomusert.mods.tincraft.fabric;

import io.github.randomusert.mods.tincraft.Tincraft;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.GenerationStep;

public final class TincraftFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.
        Tincraft.LOGGER.info("Fabric init started");

        // Run our common setup.
        Tincraft.init();

        // Adds world gen for fabric. REQUIRED for tin ore generating
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Decoration.UNDERGROUND_ORES, ResourceKey.create(Registries.PLACED_FEATURE,
                ResourceLocation.fromNamespaceAndPath(Tincraft.MOD_ID, "tin_ore")));

        Tincraft.LOGGER.info("Fabric Init Done");

    }
}
