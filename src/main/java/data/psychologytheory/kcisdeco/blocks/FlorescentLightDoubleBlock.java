package data.psychologytheory.kcisdeco.blocks;

import data.psychologytheory.kcisdeco.blocks.enums.HorizontalBlockHalf;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.tick.ScheduledTickView;
import org.jetbrains.annotations.Nullable;

public class FlorescentLightDoubleBlock extends FlorescentLightBlock
{
    private static final EnumProperty<HorizontalBlockHalf> HALF = EnumProperty.of("horizontal_half", HorizontalBlockHalf.class);
    private static final VoxelShape SHAPE = createCuboidShape(0, 8, 0, 16, 9, 16);

    public FlorescentLightDoubleBlock(Settings settings)
    {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState()
                .with(FACING, Direction.NORTH)
                .with(HALF, HorizontalBlockHalf.LEFT));
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context)
    {
        return SHAPE;
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, WorldView world, ScheduledTickView tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, Random random)
    {
        HorizontalBlockHalf thisHalf = state.get(HALF);
        Direction thisFacing = state.get(FACING);
        BlockPos otherPos = this.determineLeftPos(pos, thisFacing);

        if (thisHalf.equals(HorizontalBlockHalf.LEFT))
        {
            otherPos = this.determineRightPos(pos, thisFacing);
        }

        if (!world.getBlockState(otherPos).isOf(this)) return Blocks.AIR.getDefaultState();
        return super.getStateForNeighborUpdate(state, world, tickView, pos, direction, neighborPos, neighborState, random);
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx)
    {
        BlockPos otherPos = this.determineRightPos(ctx.getBlockPos(), ctx.getHorizontalPlayerFacing());

        if (!ctx.getWorld().getBlockState(otherPos).isOf(Blocks.AIR)) return null;

        return this.getDefaultState()
                .with(FACING, ctx.getHorizontalPlayerFacing())
                .with(HALF, HorizontalBlockHalf.LEFT);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack)
    {
        Direction facing = state.get(FACING);

        world.setBlockState(this.determineRightPos(pos, facing), state.with(HALF, HorizontalBlockHalf.RIGHT), Block.NOTIFY_ALL);
    }

    private BlockPos determineRightPos(BlockPos pos, Direction facing)
    {
        return switch (facing)
        {
            case SOUTH -> pos.west();
            case EAST -> pos.south();
            case WEST -> pos.north();
            default -> pos.east();
        };
    }

    private BlockPos determineLeftPos(BlockPos pos, Direction facing)
    {
        return switch (facing)
        {
            case SOUTH -> pos.east();
            case EAST -> pos.north();
            case WEST -> pos.south();
            default -> pos.west();
        };
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder)
    {
        builder.add(FACING, HALF);
    }
}
