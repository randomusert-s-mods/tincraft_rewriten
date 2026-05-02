package io.github.randomusert.mods.tincraft.init;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.DeferredSupplier;
import io.github.randomusert.mods.tincraft.Tincraft;
import io.github.randomusert.mods.tincraft.recipe.TinSmeltingRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

public class TCRecipeType {
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(Tincraft.MOD_ID, Registries.RECIPE_TYPE);

    public static DeferredSupplier<RecipeType<TinSmeltingRecipe>> TIN_SMELTING = RECIPE_TYPES.register("tin_smelting",
            () -> new RecipeType<>() {
                @Override
                public String toString() {
                    return "tin_smelting";
                }
            });

    public static void init() {
        RECIPE_TYPES.register();
    }
}
