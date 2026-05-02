package io.github.randomusert.mods.tincraft.block;

import com.mojang.serialization.MapCodec;
import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.InteractionEvent;
import io.github.randomusert.mods.tincraft.block.entities.TinGeneratorEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TinGeneratorBlock extends BaseEntityBlock implements InteractionEvent.RightClickBlock {
    public static final DirectionProperty FACING;

    public static final MapCodec<TinGeneratorBlock> CODEC = simpleCodec(TinGeneratorBlock::new);

    public TinGeneratorBlock(Properties props) {
        super(props);

        this.registerDefaultState((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(FACING, Direction.NORTH)));
        InteractionEvent.RIGHT_CLICK_BLOCK.register(this);
    }


    @Override
    protected void onRemove(BlockState blockState, Level level, BlockPos blockPos, BlockState blockState2, boolean bl) {
        if (!blockState.is(blockState2.getBlock())) {
            BlockEntity blockEntity = level.getBlockEntity(blockPos);
            if (blockEntity instanceof TinGeneratorEntity) {
                if (level instanceof ServerLevel) {
                    Containers.dropContents(level, blockPos, (TinGeneratorEntity) blockEntity);
                }

                super.onRemove(blockState, level, blockPos, blockState2, bl);
            } else {
                super.onRemove(blockState, level, blockPos, blockState2, bl);
            }
        }
    }

/*
    protected void openContainer(Level level, BlockPos blockPos, Player player) {
        BlockEntity be = level.getBlockEntity(blockPos);
        if (be instanceof TinGeneratorEntity) {
            player.openMenu((MenuProvider) be);

        }
    }
*/
    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        return (BlockState)this.defaultBlockState().setValue(FACING, blockPlaceContext.getHorizontalDirection().getOpposite());
    }


    @Override
    protected @NotNull InteractionResult useWithoutItem(BlockState blockState, Level level, BlockPos blockPos, Player player, BlockHitResult blockHitResult) {
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            //this.openContainer(level, blockPos, player);
            player.sendSystemMessage(Component.literal("Tin Generator right clicked"));
            return InteractionResult.CONSUME;
        }
    }



    @Override
    protected @NotNull MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public EventResult click(Player player, InteractionHand hand, BlockPos pos, Direction face) {
        return EventResult.pass();
    }

    protected @NotNull RenderShape getRenderShape(BlockState blockState) {
        return RenderShape.MODEL;
    }

    protected @NotNull BlockState rotate(BlockState blockState, Rotation rotation) {
        return (BlockState)blockState.setValue(FACING, rotation.rotate((Direction)blockState.getValue(FACING)));
    }

    protected @NotNull BlockState mirror(BlockState blockState, Mirror mirror) {
        return blockState.rotate(mirror.getRotation((Direction)blockState.getValue(FACING)));
    }

    /*
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> blockEntityType, BlockEntityType<? extends TinGeneratorEntity> blockEntityType2) {
        return level.isClientSide ? null : createTickerHelper(blockEntityType, blockEntityType2,);
    }*/

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{FACING});
    }

    static {
        FACING = HorizontalDirectionalBlock.FACING;

    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new TinGeneratorEntity(blockPos, blockState);
    }
}
