package io.github.randomusert.mods.tincraft.recipe;

import io.github.randomusert.mods.tincraft.init.TCRecipeSerializer;
import io.github.randomusert.mods.tincraft.init.TCRecipeType;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;

public class TinSmeltingRecipe extends AbstractCookingRecipe {
    public TinSmeltingRecipe(String string, CookingBookCategory cookingBookCategory, Ingredient ingredient, ItemStack itemStack, float experience, int cookingTime) {
        super(TCRecipeType.TIN_SMELTING.get(), string, cookingBookCategory, ingredient, itemStack, experience, cookingTime);
    }



    @Override
    public RecipeSerializer<?> getSerializer() {
        return TCRecipeSerializer.TIN_SMELTING.get();
    }

    @Override
    public RecipeType<?> getType() {
        return TCRecipeType.TIN_SMELTING.get();
    }

    @Override
    public CookingBookCategory category() {
        return switch (super.category()) {
            case FOOD -> CookingBookCategory.FOOD;
            case BLOCKS -> CookingBookCategory.BLOCKS;
            case MISC -> CookingBookCategory.MISC;
        };
    }


}
