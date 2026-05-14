package io.github.randomusert.mods.tincraft.init;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.DeferredSupplier;
import io.github.randomusert.mods.tincraft.Tincraft;
import io.github.randomusert.mods.tincraft.recipe.TinSmeltingRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SimpleCookingSerializer;

public class TCRecipeSerializer {
    private static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(Tincraft.MOD_ID, Registries.RECIPE_SERIALIZER);

    public static DeferredSupplier<RecipeSerializer<TinSmeltingRecipe>> TIN_SMELTING = RECIPE_SERIALIZERS.register("tin_smelting",
            () -> new SimpleCookingSerializer(TinSmeltingRecipe::new, 200));

    public static void init() {
        RECIPE_SERIALIZERS.register();
    }
}
