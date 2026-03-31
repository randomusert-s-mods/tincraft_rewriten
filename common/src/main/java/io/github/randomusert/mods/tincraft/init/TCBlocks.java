package io.github.randomusert.mods.tincraft.init;


import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.DeferredSupplier;
import io.github.randomusert.mods.tincraft.Tincraft;
import io.github.randomusert.mods.tincraft.block.Tinblock;
import io.github.randomusert.mods.tincraft.block.Tinore;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class TCBlocks {
    public static final DeferredRegister<Block> TC_BLOCKS = DeferredRegister.create(Tincraft.MOD_ID, Registries.BLOCK);

    public static final DeferredSupplier<Block> TIN_BLOCK = TC_BLOCKS.register("tin_block",
            () -> new Tinblock(BlockBehaviour.Properties.of()
                    .destroyTime(2.5f)
                    .sound(SoundType.STONE)
                    .explosionResistance(0.1f)
                    .requiresCorrectToolForDrops()));


    public static final DeferredSupplier<Block> TIN_ORE = TC_BLOCKS.register("tin_ore",
            () -> new Tinore(BlockBehaviour.Properties.of()
                    .destroyTime(2.5f)
                    .sound(SoundType.STONE)
                    .explosionResistance(0.1f)
                    .requiresCorrectToolForDrops()));

    public static void init() {
        TC_BLOCKS.register();
    }
}
