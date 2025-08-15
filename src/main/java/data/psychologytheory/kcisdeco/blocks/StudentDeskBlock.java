package data.psychologytheory.kcisdeco.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

public class StudentDeskBlock extends HorizontalFacingBlock
{
    public static final MapCodec<StudentDeskBlock> CODEC = createCodec(StudentDeskBlock::new);

    private static final VoxelShape SHAPE_NORTH = createCuboidShape(0, 0, 0, 16, 14, 14);
    private static final VoxelShape SHAPE_EAST = createCuboidShape(2, 0, 0, 16, 14, 16);
    private static final VoxelShape SHAPE_SOUTH = createCuboidShape(0, 0, 2, 16, 14, 16);
    private static final VoxelShape SHAPE_WEST = createCuboidShape(0, 0, 0, 14, 14, 16);

    public StudentDeskBlock(Settings settings)
    {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(FACING, Direction.NORTH));
    }

    @Override
    protected MapCodec<? extends HorizontalFacingBlock> getCodec()
    {
        return CODEC;
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context)
    {
        return switch (state.get(FACING))
        {
            case SOUTH -> SHAPE_SOUTH;
            case EAST -> SHAPE_EAST;
            case WEST -> SHAPE_WEST;
            default -> SHAPE_NORTH;
        };
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx)
    {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder)
    {
        builder.add(FACING);
    }
}
