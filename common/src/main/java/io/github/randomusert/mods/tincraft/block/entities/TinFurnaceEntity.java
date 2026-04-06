package io.github.randomusert.mods.tincraft.block.entities;

import io.github.randomusert.mods.tincore.block.BaseBlockEntity;
import io.github.randomusert.mods.tincraft.init.TCBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.FurnaceMenu;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class TinFurnaceEntity extends AbstractFurnaceBlockEntity {

    public TinFurnaceEntity(BlockPos blockPos, BlockState blockState) {
        super(TCBlockEntities.TIN_FURNACE.get(),blockPos, blockState, RecipeType.SMELTING);
    }

    @Override
    protected Component getDefaultName() {
        return Component.literal("Tin Furnace");
    }

    @Override
    protected AbstractContainerMenu createMenu(int i, Inventory inventory) {
        return new FurnaceMenu(i, inventory, this, this.dataAccess);
    }
}
