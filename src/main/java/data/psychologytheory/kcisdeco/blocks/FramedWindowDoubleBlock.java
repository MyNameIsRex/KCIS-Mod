package data.psychologytheory.kcisdeco.blocks;

import net.minecraft.block.*;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.tick.ScheduledTickView;
import org.jetbrains.annotations.Nullable;

public class FramedWindowDoubleBlock extends FramedWindowBlock
{
    private static final EnumProperty<DoubleBlockHalf> HALF = Properties.DOUBLE_BLOCK_HALF;

    public FramedWindowDoubleBlock(Settings settings)
    {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(HALF, DoubleBlockHalf.LOWER));
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state,
                                                   WorldView world,
                                                   ScheduledTickView tickView,
                                                   BlockPos pos,
                                                   Direction direction,
                                                   BlockPos neighborPos,
                                                   BlockState neighborState,
                                                   Random random)
    {
        DoubleBlockHalf thisHalf = state.get(HALF);
        if (thisHalf == DoubleBlockHalf.LOWER && !world.getBlockState(pos.up()).isOf(this)) return Blocks.AIR.getDefaultState();
        if (thisHalf == DoubleBlockHalf.UPPER && !world.getBlockState(pos.down()).isOf(this)) return Blocks.AIR.getDefaultState();

        return super.getStateForNeighborUpdate(state, world, tickView, pos, direction, neighborPos, neighborState, random);
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx)
    {
        BlockPos thisPosition = ctx.getBlockPos();
        World world = ctx.getWorld();

        if (thisPosition.getY() >= world.getTopYInclusive() || !world.getBlockState(thisPosition.up()).canReplace(ctx))
            return null;

        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite()).with(HALF, DoubleBlockHalf.LOWER);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack)
    {
        world.setBlockState(pos.up(), state.with(HALF, DoubleBlockHalf.UPPER), Block.NOTIFY_ALL);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder)
    {
        builder.add(FACING);
        builder.add(HALF);
    }
}
