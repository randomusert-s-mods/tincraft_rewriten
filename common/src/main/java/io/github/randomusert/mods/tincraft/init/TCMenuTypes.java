package io.github.randomusert.mods.tincraft.init;

import dev.architectury.registry.menu.MenuRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrarManager;
import dev.architectury.registry.registries.RegistrySupplier;
import io.github.randomusert.mods.tincraft.Tincraft;
import io.github.randomusert.mods.tincraft.block.entities.TinCraftingTableEntity;
import io.github.randomusert.mods.tincraft.menu.TinCraftingMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.entity.BlockEntity;
import dev.architectury.registry.menu.MenuRegistry;

public class TCMenuTypes {
    public static final RegistrarManager REGISTRIES = RegistrarManager.get(Tincraft.MOD_ID);
    public static final Registrar<MenuType<?>> MENUS = REGISTRIES.get(Registries.MENU);



    public static final RegistrySupplier<MenuType<TinCraftingMenu>> MY_MENU = MENUS.register(
            ResourceLocation.fromNamespaceAndPath(Tincraft.MOD_ID, "tin_crafting_menu"),
            () -> new MenuType<>(TinCraftingMenu::new, FeatureFlagSet.of(FeatureFlags.VANILLA))
    );
}
