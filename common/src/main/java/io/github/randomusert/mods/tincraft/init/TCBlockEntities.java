package io.github.randomusert.mods.tincraft.init;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import io.github.randomusert.mods.tincraft.Tincraft;
//import io.github.randomusert.mods.tincraft.block.entities.TinCraftingTableEntity;
import io.github.randomusert.mods.tincraft.block.entities.TinFurnaceEntity;
import io.github.randomusert.mods.tincraft.block.entities.TinGeneratorEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class TCBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Tincraft.MOD_ID, Registries.BLOCK_ENTITY_TYPE);



    public static final RegistrySupplier<BlockEntityType<TinFurnaceEntity>> TIN_FURNACE =
            BLOCK_ENTITIES.register("tin_furnace", () -> BlockEntityType.Builder.of(TinFurnaceEntity::new, TCBlocks.TIN_FURNACE.get()).build(null));

    public static final RegistrySupplier<BlockEntityType<TinGeneratorEntity>> TIN_GENERATOR =
            BLOCK_ENTITIES.register("tin_generator", () -> BlockEntityType.Builder.of(TinGeneratorEntity::new, TCBlocks.TIN_GENERATOR.get()).build(null));

    public static void init() {
        BLOCK_ENTITIES.register();
    }

}
