package io.github.randomusert.mods.tincraft.menu;

import io.github.randomusert.mods.tincraft.block.entities.TinCraftingTableEntity;
import io.github.randomusert.mods.tincraft.init.TCMenuTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class TinCraftingMenu extends AbstractContainerMenu {




    public TinCraftingMenu(int id, Inventory playerInventory, FriendlyByteBuf buf) {



        this(id, playerInventory, (TinCraftingTableEntity) playerInventory.player.level()
                .getBlockEntity(buf.readBlockPos()));

        // Add player inventory
        int startX = 8;
        int startY = 84;

        // Main inventory
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new Slot(playerInventory, col + row * 9 + 9, startX + col * 18, startY + row * 18));
            }
        }

        // Hotbar
        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(playerInventory, col, startX + col * 18, startY + 58));
        }
    }

    @Override
    public ItemStack quickMoveStack(Player player, int i) {
        return null;
    }

    @Override
    public boolean stillValid(Player player) {
        return false;
    }


}
