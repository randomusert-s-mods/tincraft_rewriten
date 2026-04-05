package io.github.randomusert.mods.tincraft.block.entities;


import io.github.randomusert.mods.tincraft.Tincraft;
import io.github.randomusert.mods.tincraft.init.TCBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;


public class TinCraftingTableEntity extends BaseContainerBlockEntity implements MenuProvider {

    private final NonNullList<ItemStack> items = NonNullList.withSize(9, ItemStack.EMPTY);

    public TinCraftingTableEntity(BlockPos pos, BlockState state) {
        super(TCBlockEntities.TIN_CRAFTING_TABLE.get(), pos, state);
    }

    // --- Inventory Methods ---
    @Override
    protected NonNullList<ItemStack> getItems() {
        return items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> items) {
        this.items.clear();
        this.items.addAll(items);
    }

    @Override
    public int getContainerSize() {
        return items.size();
    }

    // --- MenuProvider ---
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory playerInventory) {
        return new TinCraftingMenu(id, playerInventory, this);
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("container.tincraft.tin_crafting_table");
    }


    @Override
    protected void loadAdditional(CompoundTag compoundTag, HolderLookup.Provider provider) {
        super.loadAdditional(compoundTag, provider);
        ContainerHelper.loadAllItems(compoundTag, items, provider);
    }

    @Override
    protected void saveAdditional(CompoundTag compoundTag, HolderLookup.Provider provider) {
        super.saveAdditional(compoundTag, provider);
        ContainerHelper.saveAllItems(compoundTag, items, provider);
    }

    public boolean stillValid(Player player) {
        return player.distanceToSqr(worldPosition.getX() + 0.5, worldPosition.getY() + 0.5, worldPosition.getZ() + 0.5) <= 64;
    }
}
