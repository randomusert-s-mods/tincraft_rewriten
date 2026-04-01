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


    public static RegistrySupplier<CreativeModeTab> MAIN_TAB;

    public static void initTabs(){

        MAIN_TAB =
                TABS.register("main_tab", () -> CreativeTabRegistry.create(Component.translatable("creative_tab.main"), () -> new ItemStack(TCItems.TIN_INGOT.get())));



        TABS.register();
    }
}
