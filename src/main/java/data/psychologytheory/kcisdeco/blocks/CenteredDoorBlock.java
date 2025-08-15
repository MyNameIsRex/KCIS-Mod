package data.psychologytheory.kcisdeco.blocks;

import net.minecraft.block.*;
import net.minecraft.block.enums.DoorHinge;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class CenteredDoorBlock extends DoorBlock
{
    private static final Map<Direction, VoxelShape> SHAPES_CLOSED = VoxelShapes.createHorizontalFacingShapeMap(Block.createCuboidZShape(16.0, 7.0, 9.0));
    private static final Map<Direction, VoxelShape> SHAPES_OPENED_LEFT = VoxelShapes.createHorizontalFacingShapeMap(Block.createCuboidShape(0.0, 0.0, 0.0, 2.0, 16.0, 9.0));
    private static final Map<Direction, VoxelShape> SHAPES_OPENED_RIGHT = VoxelShapes.createHorizontalFacingShapeMap(Block.createCuboidShape(14.0, 0.0, 0.0, 16.0, 16.0, 9.0));

    public CenteredDoorBlock(BlockSetType type, Settings settings)
    {
        super(type, settings);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context)
    {
        Direction facing = state.get(FACING);

        if (!state.get(OPEN)) return SHAPES_CLOSED.get(facing);
        return state.get(HINGE) == DoorHinge.LEFT ? SHAPES_OPENED_LEFT.get(facing) : SHAPES_OPENED_RIGHT.get(facing);
    }
}
