package io.github.randomusert.mods.tincraft.init;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import io.github.randomusert.mods.tincraft.Tincraft;
import io.github.randomusert.mods.tincraft.block.entities.TinCraftingTableEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class TCBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Tincraft.MOD_ID, Registries.BLOCK_ENTITY_TYPE);

    public static final RegistrySupplier<BlockEntityType<TinCraftingTableEntity>> TIN_CRAFTING_TABLE =
            BLOCK_ENTITIES.register("tin_crafting_table", () -> BlockEntityType.Builder.of(TinCraftingTableEntity::new, TCBlocks.TIN_CRAFTING_TABLE.get()).build(null));

    public static void init() {
        BLOCK_ENTITIES.register();
    }

}
