package data.psychologytheory.kcisdeco.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.tick.ScheduledTickView;
import org.jetbrains.annotations.Nullable;

public class BoardBlock extends HorizontalFacingBlock
{
    public static final MapCodec<BoardBlock> CODEC = createCodec(BoardBlock::new);
    private static final BooleanProperty LEFT = BooleanProperty.of("left");
    private static final BooleanProperty RIGHT = BooleanProperty.of("right");
    private static final BooleanProperty UP = ConnectingBlock.UP;
    private static final BooleanProperty DOWN = ConnectingBlock.DOWN;


    private static final VoxelShape SHAPE_NORTH = createCuboidShape(0, 0, 13, 16, 16,16);
    private static final VoxelShape SHAPE_SOUTH = createCuboidShape(0, 0, 0, 16, 16,3);
    private static final VoxelShape SHAPE_WEST = createCuboidShape(13, 0, 0, 16, 16,16);

    private static final VoxelShape SHAPE_EAST = createCuboidShape(0, 0, 0, 3, 16,16);

    public BoardBlock(Settings settings)
    {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState()
                .with(FACING, Direction.NORTH)
                .with(LEFT, false)
                .with(RIGHT, false)
                .with(UP, false)
                .with(DOWN, false));
    }

    @Override
    protected MapCodec<? extends HorizontalFacingBlock> getCodec()
    {
        return CODEC;
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, WorldView world, ScheduledTickView tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, Random random)
    {
        Direction thisFacing = state.get(FACING);

        return this.getConnectionState(world, thisFacing, pos);
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx)
    {
        World world = ctx.getWorld();
        Direction thisFacing = ctx.getHorizontalPlayerFacing().getOpposite();
        BlockPos thisPosition = ctx.getBlockPos();

        return this.getConnectionState(world, thisFacing, thisPosition);
    }

    private BlockState getConnectionState(WorldView world, Direction thisFacing, BlockPos thisPosition)
    {
        BlockState thisState = world.getBlockState(thisPosition),
                leftState,
                rightState,
                upState = world.getBlockState(thisPosition.up()),
                downState = world.getBlockState(thisPosition.down());

        switch(thisFacing)
        {
            case SOUTH ->
            {
                leftState = world.getBlockState(thisPosition.west());
                rightState = world.getBlockState(thisPosition.east());
            }

            case EAST ->
            {
                leftState = world.getBlockState(thisPosition.south());
                rightState = world.getBlockState(thisPosition.north());
            }

            case WEST ->
            {
                leftState = world.getBlockState(thisPosition.north());
                rightState = world.getBlockState(thisPosition.south());
            }


            default ->
            {
                leftState = world.getBlockState(thisPosition.east());
                rightState = world.getBlockState(thisPosition.west());
            }
        }

        boolean connectLeft = leftState.isOf(this) && thisState.isOf(this) && leftState.get(FACING).equals(thisFacing),
                connectRight = rightState.isOf(this) && thisState.isOf(this) && rightState.get(FACING).equals(thisFacing),
                connectingUp = upState.isOf(this) && thisState.isOf(this) && upState.get(FACING).equals(thisFacing),
                connectingDown = downState.isOf(this) && thisState.isOf(this) && downState.get(FACING).equals(thisFacing);

        return this.getDefaultState()
                .with(FACING, thisFacing)
                .with(LEFT, connectLeft)
                .with(RIGHT, connectRight)
                .with(UP, connectingUp)
                .with(DOWN, connectingDown);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context)
    {
        return switch(state.get(FACING))
        {
            case SOUTH -> SHAPE_SOUTH;
            case EAST -> SHAPE_EAST;
            case WEST -> SHAPE_WEST;
            default -> SHAPE_NORTH;
        };
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder)
    {
        builder.add(FACING, LEFT, RIGHT, UP, DOWN);
    }
}
