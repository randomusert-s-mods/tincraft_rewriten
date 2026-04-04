package io.github.randomusert.mods.tincraft.fabric.blocks;

import io.github.randomusert.mods.tincraft.block.TCBlockEntityTypes;
import io.github.randomusert.mods.tincraft.block.TinCraftingTableEntity;
import io.github.randomusert.mods.tincraft.init.TCBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;

public class TCBlockEntityTypesFabric extends TCBlockEntityTypes {
    public static void initBlockEntityTypes() {
        TIN_CRAFTING_TABLE = registerBlockEntity("tin_crafting_table", ()-> FabricBlockEntityTypeBuilder.create(TinCraftingTableEntity::new,
                TCBlocks.TIN_CRAFTING_TABLE.get()).build());

        writeRegister();
    }
}
