package io.github.randomusert.mods.tincraft.init;

import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import io.github.randomusert.mods.tincraft.Tincraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class TCTabs {
    private static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Tincraft.MOD_ID, Registries.CREATIVE_MODE_TAB);

    // Register your custom tab
    public static final CreativeModeTab MAIN_TAB = CreativeTabRegistry.create(builder -> {
        builder.title(Component.translatable("creative_tab.main"));
        builder.icon(() -> new ItemStack(Items.DIAMOND));
        builder.displayItems((params, output) -> {
            output.accept(new ItemStack(TCItems.TIN_INGOT.get()));
            output.accept(new ItemStack(TCItems.TIN_BLOCK_ITEM.get()));
            output.accept(new ItemStack(TCItems.TIN_ORE_ITEM.get()));
            output.accept(new ItemStack(Items.GOLD_INGOT));
        });
    });

    public static void initTabs(){


        TABS.register();
    }
}
