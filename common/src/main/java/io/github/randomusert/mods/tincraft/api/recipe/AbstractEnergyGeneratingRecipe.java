package io.github.randomusert.mods.tincraft.api.recipe;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

public abstract class AbstractEnergyGeneratingRecipe implements Recipe<SingleRecipeInput> {
    protected final RecipeType<?> type;
    protected final CookingBookCategory category;
    protected final String group;
    protected final Ingredient ingredient;
    protected final int result;

    protected final int cookingTime;

    public AbstractEnergyGeneratingRecipe(RecipeType<?> type, String string, CookingBookCategory category, Ingredient ingredient, int output, float f, int i) {
        this.type = type;
        this.category = category;
        this.group = string;
        this.ingredient = ingredient;
        this.result = output;
        this.cookingTime = i;
    }


    public boolean matches(SingleRecipeInput recipeInput, Level level) {
        return this.ingredient.test(recipeInput.item());
    }

    public boolean canCraftInDimensions(int i, int j) {
        return true;
    }

    public String getGroup() {
        return this.group;
    }
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> nonNullList = NonNullList.create();
        nonNullList.add(this.ingredient);
        return nonNullList;
    }


    public int getResult() {
        return this.result;
    }



    public RecipeType<?> getType() {
        return this.type;
    }
}
