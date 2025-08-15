package data.psychologytheory.kcisdeco.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ConnectingBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.WorldView;
import net.minecraft.world.tick.ScheduledTickView;
import org.jetbrains.annotations.Nullable;

public class FramedWindowExtendableBlock extends FramedWindowBlock
{
    public static final BooleanProperty NORTH = ConnectingBlock.NORTH;
    public static final BooleanProperty EAST = ConnectingBlock.EAST;
    public static final BooleanProperty SOUTH = ConnectingBlock.SOUTH;
    public static final BooleanProperty WEST = ConnectingBlock.WEST;
    public static final BooleanProperty UP = ConnectingBlock.UP;
    public static final BooleanProperty DOWN = ConnectingBlock.DOWN;

    public FramedWindowExtendableBlock(Settings settings)
    {
        super(settings);
        this.setDefaultState(this.getDefaultState()
                .with(FACING, Direction.NORTH)
                .with(NORTH, false)
                .with(EAST, false)
                .with(SOUTH, false)
                .with(WEST, false)
                .with(UP, false)
                .with(DOWN, false));
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
        Direction blockFacing = state.get(FACING);
        return this.determineConnectingState(blockFacing, pos, world);
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx)
    {
        BlockPos thisPosition = ctx.getBlockPos();
        Direction blockFacing = ctx.getHorizontalPlayerFacing().getOpposite();
        WorldView world = ctx.getWorld();

        return this.determineConnectingState(blockFacing, thisPosition, world);
    }

    private BlockState determineConnectingState(Direction blockFacing, BlockPos thisPosition, WorldView world)
    {
        BlockPos northPosition = thisPosition.north(),
                eastPosition = thisPosition.east(),
                southPosition = thisPosition.south(),
                westPosition = thisPosition.west(),
                topPosition = thisPosition.up(),
                bottomPosition = thisPosition.down();

        BlockState thisBlockState = world.getBlockState(thisPosition),
                northBlockState = world.getBlockState(northPosition),
                eastBlockState = world.getBlockState(eastPosition),
                southBlockState = world.getBlockState(southPosition),
                westBlockState = world.getBlockState(westPosition),
                topBlockState = world.getBlockState(topPosition),
                bottomBlockState = world.getBlockState(bottomPosition);

        boolean connectNorth = northBlockState.isOf(this) && thisBlockState.isOf(this)
                        && (northBlockState.get(FACING).equals(thisBlockState.get(FACING)) || northBlockState.get(FACING).equals(thisBlockState.get(FACING).getOpposite())),
                connectEast = eastBlockState.isOf(this) && thisBlockState.isOf(this)
                        && (eastBlockState.get(FACING).equals(thisBlockState.get(FACING)) || eastBlockState.get(FACING).equals(thisBlockState.get(FACING).getOpposite())),
                connectSouth = southBlockState.isOf(this) && thisBlockState.isOf(this)
                        && (southBlockState.get(FACING).equals(thisBlockState.get(FACING)) || southBlockState.get(FACING).equals(thisBlockState.get(FACING).getOpposite())),
                connectWest = westBlockState.isOf(this) && thisBlockState.isOf(this)
                        && (westBlockState.get(FACING).equals(thisBlockState.get(FACING)) || westBlockState.get(FACING).equals(thisBlockState.get(FACING).getOpposite())),
                connectTop = topBlockState.isOf(this) && thisBlockState.isOf(this)
                        && (topBlockState.get(FACING).equals(thisBlockState.get(FACING)) || topBlockState.get(FACING).equals(thisBlockState.get(FACING).getOpposite())),
                connectBottom = bottomBlockState.isOf(this) && thisBlockState.isOf(this)
                        && (bottomBlockState.get(FACING).equals(thisBlockState.get(FACING)) || bottomBlockState.get(FACING).equals(thisBlockState.get(FACING).getOpposite()));

        if (blockFacing.equals(Direction.SOUTH) || blockFacing.equals(Direction.NORTH))
        {
            connectNorth = false;
            connectSouth = false;
        }

        if (blockFacing.equals(Direction.WEST) || blockFacing.equals(Direction.EAST))
        {
            connectEast = false;
            connectWest = false;
        }

        return this.getDefaultState()
                .with(FACING, blockFacing)
                .with(NORTH, connectNorth)
                .with(EAST, connectEast)
                .with(SOUTH, connectSouth)
                .with(WEST, connectWest)
                .with(UP, connectTop)
                .with(DOWN, connectBottom);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder)
    {
        builder.add(FACING, NORTH, EAST, SOUTH, WEST, UP, DOWN);
    }
}
