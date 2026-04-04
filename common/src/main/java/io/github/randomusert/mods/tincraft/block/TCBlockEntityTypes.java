package io.github.randomusert.mods.tincraft.block;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import io.github.randomusert.mods.tincraft.Tincraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Supplier;

public class TCBlockEntityTypes {

    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES
            = DeferredRegister.create(Tincraft.MOD_ID, Registries.BLOCK_ENTITY_TYPE);


    public static RegistrySupplier<BlockEntityType<TinCraftingTableEntity>> TIN_CRAFTING_TABLE;


    public static void writeRegister(){
        BLOCK_ENTITIES.register();
    }


    public static <T extends BlockEntityType<?>> RegistrySupplier<T> registerBlockEntity(
            String name, Supplier<T> blockEntity
    ){
        return BLOCK_ENTITIES.register(ResourceLocation.fromNamespaceAndPath(Tincraft.MOD_ID, name), blockEntity);
    }
}
