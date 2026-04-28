package io.github.randomusert.mods.tincraft.item;

import io.github.randomusert.mods.tincore.item.BaseItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class ConductiveTinIngot extends BaseItem {
    public ConductiveTinIngot(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext tooltipContext, List<Component> list, TooltipFlag tooltipFlag) {
        list.add(Component.translatable("tooltip.tincraft.conductive_tin_ingot"));
    }
}
