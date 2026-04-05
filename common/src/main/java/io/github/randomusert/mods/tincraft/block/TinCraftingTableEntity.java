package io.github.randomusert.mods.tincraft.block;


import io.github.randomusert.mods.tincraft.Tincraft;
import io.github.randomusert.mods.tincraft.init.TCBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;


public class TinCraftingTableEntity extends BlockEntity {



    public TinCraftingTableEntity(BlockPos pos, BlockState state) {
        super(TCBlockEntities.TIN_CRAFTING_TABLE.get(), pos, state);

    }

    public static class Ticker<T extends BlockEntity> implements BlockEntityTicker<T> {
        @Override
        public void tick(Level level, BlockPos blockPos, BlockState blockState, T blockEntity) {
            Tincraft.LOGGER.info("Test INFO message from ticker class for TinCraftingTable");
        }
    }


}
