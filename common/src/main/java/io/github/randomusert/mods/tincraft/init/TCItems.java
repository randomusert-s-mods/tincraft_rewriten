package io.github.randomusert.mods.tincraft.init;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.DeferredSupplier;
import io.github.randomusert.mods.tincraft.Tincraft;
import io.github.randomusert.mods.tincraft.item.TinIngot;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

import static io.github.randomusert.mods.tincraft.init.TCTabs.MAIN_TAB;

public class TCItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Tincraft.MOD_ID, Registries.ITEM);


    // normal item registry modifications
    public static final DeferredSupplier<Item> TIN_INGOT = ITEMS.register(
            "tin_ingot",
            () -> new TinIngot(new Item.Properties().arch$tab(MAIN_TAB))
    );

    // adding block items to the item registry
    public static final DeferredSupplier<BlockItem> TIN_BLOCK_ITEM = ITEMS.register("tin_block",
            () -> new BlockItem(TCBlocks.TIN_BLOCK.get(), new Item.Properties().arch$tab(MAIN_TAB)));

    public static final DeferredSupplier<BlockItem> TIN_ORE_ITEM = ITEMS.register("tin_ore",
            () -> new BlockItem(TCBlocks.TIN_ORE.get(), new Item.Properties().arch$tab(MAIN_TAB)));


     public static final DeferredSupplier<BlockItem> TIN_FURNACE = ITEMS.register("tin_furnace",
            () -> new BlockItem(TCBlocks.TIN_FURNACE.get(), new Item.Properties().arch$tab(MAIN_TAB)));


    public static void init() {
        ITEMS.register();
    }
}
