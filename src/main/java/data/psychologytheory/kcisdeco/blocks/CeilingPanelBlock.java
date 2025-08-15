package data.psychologytheory.kcisdeco.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.SlabBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class CeilingPanelBlock extends Block
{
    private static final VoxelShape SHAPE_TOP = createCuboidShape(0, 8, 0, 16, 9, 16);
    private static final VoxelShape SHAPE_BOTTOM = createCuboidShape(0, 0, 0, 16, 1, 16);

    private final boolean isTop;

    public CeilingPanelBlock(Settings settings, boolean isTop)
    {
        super(settings);
        this.isTop = isTop;
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context)
    {
        if (this.isTop) return SHAPE_TOP;
        return SHAPE_BOTTOM;
    }
}
