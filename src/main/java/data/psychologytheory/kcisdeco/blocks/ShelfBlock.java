package data.psychologytheory.kcisdeco.blocks;

import data.psychologytheory.kcisdeco.blocks.blockentities.ShelfBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.WorldView;
import net.minecraft.world.tick.ScheduledTickView;
import org.jetbrains.annotations.Nullable;

public class ShelfBlock extends StorageBlock
{
    private static final BooleanProperty LEFT = BooleanProperty.of("left");
    private static final BooleanProperty RIGHT = BooleanProperty.of("right");
    private static final BooleanProperty TOP = BooleanProperty.of("top");

    public ShelfBlock(Settings settings)
    {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState()
                .with(FACING, Direction.NORTH)
                .with(LEFT, false)
                .with(RIGHT, false)
                .with(TOP, false));
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, WorldView world, ScheduledTickView tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, Random random)
    {
        Direction thisFacing = world.getBlockState(pos).get(FACING);
        return this.determineConnectionState(thisFacing, pos, world);
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx)
    {
        BlockPos thisPosition = ctx.getBlockPos();
        Direction blockFacing = ctx.getHorizontalPlayerFacing().getOpposite();
        WorldView world = ctx.getWorld();

        return this.determineConnectionState(blockFacing, thisPosition, world);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state)
    {
        return new ShelfBlockEntity(pos, state);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder)
    {
        builder.add(FACING).add(LEFT).add(RIGHT).add(TOP);
    }

    private BlockState determineConnectionState(Direction thisFacing, BlockPos thisPosition, WorldView world)
    {
        BlockState thisState = world.getBlockState(thisPosition),
                leftState,
                rightState,
                upState = world.getBlockState(thisPosition.up());

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
                connectingUp = upState.isOf(this) && thisState.isOf(this) && upState.get(FACING).equals(thisFacing);

        return this.getDefaultState()
                .with(FACING, thisFacing)
                .with(LEFT, connectLeft)
                .with(RIGHT, connectRight)
                .with(TOP, !connectingUp);
    }
}
