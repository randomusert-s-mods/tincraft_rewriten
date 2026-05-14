package io.github.randomusert.mods.tincraft.api.block.entities;

import com.google.common.collect.Maps;
import io.github.randomusert.mods.tincraft.api.recipe.AbstractEnergyGeneratingRecipe;
import io.github.randomusert.mods.tincraft.init.TCItems;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.SharedConstants;
import net.minecraft.Util;
import net.minecraft.core.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeCraftingHolder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

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
    int litTime;
    private final RecipeManager.CachedCheck<SingleRecipeInput, ? extends AbstractEnergyGeneratingRecipe> quickCheck;
    @Nullable
    private static volatile Map<Item, Integer> fuelCache;
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
        this.quickCheck = RecipeManager.createCheck(recipeType);
    }

    public static void invalidateCache() {
        fuelCache = null;
    }

    public static Map<Item, Integer> getFuel() {
        Map<Item, Integer> map = fuelCache;
        if (map != null) {
            return map;
        } else {
            Map<Item, Integer> map2 = Maps.newLinkedHashMap();
            add(map2, Items.LAVA_BUCKET, 20000);
            add(map2, Blocks.COAL_BLOCK, 16000);
            add(map2, Items.BLAZE_ROD, 2400);
            add(map2, ItemTags.COALS, 1600);
            add(map2, ItemTags.LOGS, 300);
            add(map2, ItemTags.BAMBOO_BLOCKS, 300);
            add(map2, ItemTags.PLANKS, 300);
            add(map2, Blocks.BAMBOO_MOSAIC, 300);
            add(map2, ItemTags.WOODEN_STAIRS, 300);
            add(map2, Blocks.BAMBOO_MOSAIC_STAIRS, 300);
            add(map2, ItemTags.WOODEN_SLABS, 150);
            add(map2, Blocks.BAMBOO_MOSAIC_SLAB, 150);
            add(map2, ItemTags.WOODEN_TRAPDOORS, 300);
            add(map2, ItemTags.WOODEN_PRESSURE_PLATES, 300);
            add(map2, ItemTags.WOODEN_FENCES, 300);
            add(map2, ItemTags.FENCE_GATES, 300);
            add(map2, Blocks.NOTE_BLOCK, 300);
            add(map2, Blocks.BOOKSHELF, 300);
            add(map2, Blocks.CHISELED_BOOKSHELF, 300);
            add(map2, Blocks.LECTERN, 300);
            add(map2, Blocks.JUKEBOX, 300);
            add(map2, Blocks.CHEST, 300);
            add(map2, Blocks.TRAPPED_CHEST, 300);
            add(map2, Blocks.CRAFTING_TABLE, 300);
            add(map2, Blocks.DAYLIGHT_DETECTOR, 300);
            add(map2, ItemTags.BANNERS, 300);
            add(map2, Items.BOW, 300);
            add(map2, Items.FISHING_ROD, 300);
            add(map2, Blocks.LADDER, 300);
            add(map2, ItemTags.SIGNS, 200);
            add(map2, ItemTags.HANGING_SIGNS, 800);
            add(map2, Items.WOODEN_SHOVEL, 200);
            add(map2, Items.WOODEN_SWORD, 200);
            add(map2, Items.WOODEN_HOE, 200);
            add(map2, Items.WOODEN_AXE, 200);
            add(map2, Items.WOODEN_PICKAXE, 200);
            add(map2, ItemTags.WOODEN_DOORS, 200);
            add(map2, ItemTags.BOATS, 1200);
            add(map2, ItemTags.WOOL, 100);
            add(map2, ItemTags.WOODEN_BUTTONS, 100);
            add(map2, Items.STICK, 100);
            add(map2, ItemTags.SAPLINGS, 100);
            add(map2, Items.BOWL, 100);
            add(map2, ItemTags.WOOL_CARPETS, 67);
            add(map2, Blocks.DRIED_KELP_BLOCK, 4001);
            add(map2, Items.CROSSBOW, 300);
            add(map2, Blocks.BAMBOO, 50);
            add(map2, Blocks.DEAD_BUSH, 100);
            add(map2, Blocks.SCAFFOLDING, 50);
            add(map2, Blocks.LOOM, 300);
            add(map2, Blocks.BARREL, 300);
            add(map2, Blocks.CARTOGRAPHY_TABLE, 300);
            add(map2, Blocks.FLETCHING_TABLE, 300);
            add(map2, Blocks.SMITHING_TABLE, 300);
            add(map2, Blocks.COMPOSTER, 300);
            add(map2, Blocks.AZALEA, 100);
            add(map2, Blocks.FLOWERING_AZALEA, 100);
            add(map2, Blocks.MANGROVE_ROOTS, 300);
            add(map2, TCItems.TIN_INGOT.get(), 100);
            fuelCache = map2;
            return map2;
        }
    }

    private static boolean isNeverAFurnaceFuel(Item item) {
        return item.builtInRegistryHolder().is(ItemTags.NON_FLAMMABLE_WOOD);
    }

    private static void add(Map<Item, Integer> map, TagKey<Item> tagKey, int i) {
        for(Holder<Item> holder : BuiltInRegistries.ITEM.getTagOrEmpty(tagKey)) {
            if (!isNeverAFurnaceFuel((Item)holder.value())) {
                map.put((Item)holder.value(), i);
            }
        }

    }

    private static void add(Map<Item, Integer> map, ItemLike itemLike, int i) {
        Item item = itemLike.asItem();
        if (isNeverAFurnaceFuel(item)) {
            if (SharedConstants.IS_RUNNING_IN_IDE) {
                throw (IllegalStateException) Util.pauseInIde(new IllegalStateException("A developer tried to explicitly make fire resistant item " + item.getName((ItemStack)null).getString() + " a furnace fuel. That will not work!"));
            }
        } else {
            map.put(item, i);
        }
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


    private boolean isLit() {
        return this.litTime > 0;
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
        boolean bl = abstractGeneratorBlockEntity.isLit();
        boolean bl2 = false;
        if (abstractGeneratorBlockEntity.isLit()) {
            --abstractGeneratorBlockEntity.litTime;
        }

        ItemStack itemStack = (ItemStack)abstractGeneratorBlockEntity.items.get(0);

        boolean isItemStackEmpty = !itemStack.isEmpty();


        if (abstractGeneratorBlockEntity.isLit() || isItemStackEmpty) {
            RecipeHolder<?> recipeHolder;

            int i = abstractGeneratorBlockEntity.getMaxStackSize();
            if (isItemStackEmpty) {
                recipeHolder = (RecipeHolder) abstractGeneratorBlockEntity.quickCheck.getRecipeFor(new SingleRecipeInput(itemStack), level).orElse(null);
            } else {
                recipeHolder = null;
            }

            if (!abstractGeneratorBlockEntity.isLit() && canBurn(evel.registryAccess(), recipeHolder, abstractGeneratorBlockEntity.items, i)) {
                abstractGeneratorBlockEntity.litTime = abstractGeneratorBlockEntity.getBurnDuration(itemStack);

            }
        }

    }

    private static boolean canBurn(RegistryAccess registryAccess, @Nullable RecipeHolder<?> recipeHolder, NonNullList<ItemStack> nonNullList, int i) {
        if (!((ItemStack)nonNullList.get(0)).isEmpty() && recipeHolder != null) {
            ItemStack itemStack = recipeHolder.value().getResultItem(registryAccess);
            if (itemStack.isEmpty()) {
                return false;
            } else {

                if (itemStack2.isEmpty()) {
                    return true;
                } else if (!ItemStack.isSameItemSameComponents(itemStack2, itemStack)) {
                    return false;
                } else if (itemStack2.getCount() < i && itemStack2.getCount() < itemStack2.getMaxStackSize()) {
                    return true;
                } else {
                    return itemStack2.getCount() < itemStack.getMaxStackSize();
                }
            }
        } else {
            return false;
        }
    }

    private static boolean burn(RegistryAccess registryAccess, @Nullable RecipeHolder<?> recipeHolder, NonNullList<ItemStack> nonNullList, int i) {
        if (recipeHolder != null && canBurn(registryAccess, recipeHolder, nonNullList, i)) {
            ItemStack itemStack = (ItemStack)nonNullList.get(0);
            ItemStack itemStack2 = recipeHolder.value().getResultItem(registryAccess);
            ItemStack itemStack3 = (ItemStack)nonNullList.get(2);
            if (itemStack3.isEmpty()) {
                nonNullList.set(2, itemStack2.copy());
            } else if (ItemStack.isSameItemSameComponents(itemStack3, itemStack2)) {
                itemStack3.grow(1);
            }

            if (itemStack.is(Blocks.WET_SPONGE.asItem()) && !((ItemStack)nonNullList.get(1)).isEmpty() && ((ItemStack)nonNullList.get(1)).is(Items.BUCKET)) {
                nonNullList.set(1, new ItemStack(Items.WATER_BUCKET));
            }

            itemStack.shrink(1);
            return true;
        } else {
            return false;
        }
    }

    protected int getBurnDuration(ItemStack itemStack) {
        if (itemStack.isEmpty()) {
            return 0;
        } else {
            Item item = itemStack.getItem();
            return (Integer)getFuel().getOrDefault(item, 0);
        }
    }
}
