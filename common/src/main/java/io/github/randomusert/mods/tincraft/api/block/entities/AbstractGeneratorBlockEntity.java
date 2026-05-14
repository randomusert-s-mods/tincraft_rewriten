package io.github.randomusert.mods.tincraft.api.block.entities;

import io.github.randomusert.mods.tincraft.api.recipe.AbstractEnergyGeneratingRecipe;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeCraftingHolder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class AbstractGeneratorBlockEntity extends BaseContainerBlockEntity implements WorldlyContainer, RecipeCraftingHolder {


    /// -----VARIABLES-----
    ///
    /// These variables are used to define internal factors to the block
    ///
    /// These don't need to be over writen as these values are base values
    protected static final int SLOT_INPUT = 0;
    public static final int BURN_TIME_STANDARD = 200;
    public static final int NUM_DATA_VALUES = 6;
    protected NonNullList<ItemStack> items;
    int capacity;
    int maxOut;
    int currentEnergy;
    int maxBurnLength;
    int cookingProgress;
    int cookingTotalTime;
    private final Object2IntOpenHashMap<ResourceLocation> recipesUsed;
    protected final ContainerData dataAccess;


    //-----CONSTRUCTOR-----
    protected AbstractGeneratorBlockEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState, RecipeType<? extends AbstractEnergyGeneratingRecipe> recipeType) {
        super(blockEntityType, blockPos, blockState);
        this.items = NonNullList.withSize(1,ItemStack.EMPTY);

        this.dataAccess = new ContainerData() {
            public int get(int i) {
                switch (i) {
                    case 0 -> {
                        return AbstractGeneratorBlockEntity.this.capacity;
                    }
                    case 1 -> {
                        return AbstractGeneratorBlockEntity.this.maxOut;
                    }
                    case 2 -> {
                        return AbstractGeneratorBlockEntity.this.currentEnergy;
                    }
                    case 3 -> {
                        return AbstractGeneratorBlockEntity.this.maxBurnLength;
                    }
                    case 4 -> {
                        return AbstractGeneratorBlockEntity.this.cookingProgress;
                    }
                    case 5 -> {
                        return AbstractGeneratorBlockEntity.this.cookingTotalTime;
                    }
                    default -> {
                        return 0;
                    }
                }
            }

            public void set(int i, int j) {
                switch (i) {
                    case 0 -> AbstractGeneratorBlockEntity.this.capacity = j;
                    case 1 -> AbstractGeneratorBlockEntity.this.maxOut = j;
                    case 2 -> AbstractGeneratorBlockEntity.this.currentEnergy = j;
                    case 3 -> AbstractGeneratorBlockEntity.this.maxBurnLength = j;
                    case 4 -> AbstractGeneratorBlockEntity.this.cookingProgress = j;
                    case 5 -> AbstractGeneratorBlockEntity.this.cookingTotalTime = j;
                }

            }

            public int getCount() {
                return 6;
            }
        };
        this.recipesUsed = new Object2IntOpenHashMap();
    }

   /// A consumer must @override this method
   ///
   /// Gets the default name as the name suggests
    @Override
    protected Component getDefaultName() {
        return null;
    }


    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> nonNullList) {
        this.items = nonNullList;
    }

    /// Creates a menu
    ///
    /// Must be annotated with @override
    @Override
    protected AbstractContainerMenu createMenu(int i, Inventory inventory) {
        return null;
    }

    @Override
    public int getContainerSize() {
        return 0;
    }

    @Override
    public int getMaxStackSize() {
        return super.getMaxStackSize();
    }

    @Override
    public int[] getSlotsForFace(Direction direction) {
        return new int[0];
    }

    @Override
    public boolean canPlaceItemThroughFace(int i, ItemStack itemStack, @Nullable Direction direction) {
        return false;
    }

    @Override
    public boolean canTakeItemThroughFace(int i, ItemStack itemStack, Direction direction) {
        return false;
    }

    @Override
    public void setRecipeUsed(@Nullable RecipeHolder<?> recipeHolder) {

    }

    @Override
    public @Nullable RecipeHolder<?> getRecipeUsed() {
        return null;
    }

    @Override
    protected void loadAdditional(CompoundTag compoundTag, HolderLookup.Provider provider) {
        super.loadAdditional(compoundTag, provider);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(compoundTag, this.items, provider);
        this.capacity = compoundTag.getShort("Capacity");
        this.currentEnergy = compoundTag.getShort("StoredEnergy");
        this.maxBurnLength = compoundTag.getShort("MaxBurnLength");
        CompoundTag compoundTag2 = compoundTag.getCompound("RecipesUsed");

        for(String string : compoundTag2.getAllKeys()) {
            this.recipesUsed.put(ResourceLocation.parse(string), compoundTag2.getInt(string));
        }
    }

    @Override
    protected void saveAdditional(CompoundTag compoundTag, HolderLookup.Provider provider) {
        super.saveAdditional(compoundTag, provider);
        compoundTag.putShort("Capacity", (short)this.capacity);
        compoundTag.putShort("StoredEnergy", (short)this.currentEnergy);
        compoundTag.putShort("MaxBurnLength", (short)this.maxBurnLength);
        ContainerHelper.saveAllItems(compoundTag, this.items, provider);
        CompoundTag compoundTag2 = new CompoundTag();
        this.recipesUsed.forEach((resourceLocation, integer) -> compoundTag2.putInt(resourceLocation.toString(), integer));
        compoundTag.put("RecipesUsed", compoundTag2);
    }

    public static void serverTick(Level level, BlockPos blockPos, BlockState blockState, AbstractGeneratorBlockEntity abstractGeneratorBlockEntity) {
        ItemStack itemStack = (ItemStack)abstractGeneratorBlockEntity.items.get(0);

        boolean isItemStackEmpty = !itemStack.isEmpty();

    }
}
