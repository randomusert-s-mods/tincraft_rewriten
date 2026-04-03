package io.github.randomusert.mods.tincraft.block;


import io.github.randomusert.mods.tincraft.init.TCBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;


public class TinCraftingTableEntity extends BlockEntity {



    public TinCraftingTableEntity(BlockPos pos, BlockState state) {
        super(TCBlockEntities.BASIC_TIN_ENERGY_CUBE.get(), pos, state);

    }






}
