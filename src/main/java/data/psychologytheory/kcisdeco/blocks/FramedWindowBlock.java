package data.psychologytheory.kcisdeco.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

public class FramedWindowBlock extends HorizontalFacingBlock
{
    public static final MapCodec<FramedWindowBlock> CODEC = createCodec(FramedWindowBlock::new);
    protected static final VoxelShape SHAPE_NS = Block.createCuboidShape(0, 0, 7, 16, 16, 9);
    protected static final VoxelShape SHAPE_EW = Block.createCuboidShape(7, 0, 0, 9, 16, 16);

    public FramedWindowBlock(Settings settings)
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
        Direction blockFacing = state.get(FACING);

        return switch (blockFacing)
        {
            case EAST, WEST -> SHAPE_EW;
            default -> SHAPE_NS;
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
