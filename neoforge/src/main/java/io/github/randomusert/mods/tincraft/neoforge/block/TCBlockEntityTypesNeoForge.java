package io.github.randomusert.mods.tincraft.neoforge.block;

import io.github.randomusert.mods.tincraft.block.TCBlockEntityTypes;
import io.github.randomusert.mods.tincraft.block.TinCraftingTableEntity;
import io.github.randomusert.mods.tincraft.init.TCBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class TCBlockEntityTypesNeoForge extends TCBlockEntityTypes {
    public static void initBlockEntityTypes() {
        TIN_CRAFTING_TABLE = registerBlockEntity("tin_crafting_Table",() ->
                new BlockEntityType<>(TinCraftingTableEntity::new, TCBlocks.TIN_CRAFTING_TABLE.get()));

        writeRegister();
    }
}
