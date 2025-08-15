package data.psychologytheory.kcisdeco.blocks;

import com.mojang.serialization.MapCodec;
import data.psychologytheory.kcisdeco.blocks.blockentities.RoomSignBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class RoomSignBlock extends BlockWithEntity
{
    public static final EnumProperty<Direction> FACING = HorizontalFacingBlock.FACING;
    public static final MapCodec<RoomSignBlock> CODEC = createCodec(RoomSignBlock::new);
    private static final VoxelShape SHAPE_NS = Block.createCuboidShape(0, 0, 7, 16, 16, 9);
    private static final VoxelShape SHAPE_EW = Block.createCuboidShape(7, 0, 0, 9, 16, 16);

    public RoomSignBlock(Settings settings)
    {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(FACING, Direction.NORTH));
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec()
    {
        return CODEC;
    }
    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context)
    {
        Direction blockFacing = state.get(FACING);

        if (blockFacing.equals(Direction.EAST) || blockFacing.equals(Direction.WEST)) return SHAPE_EW;
        return SHAPE_NS;
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

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state)
    {
        return new RoomSignBlockEntity(pos, state);
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state)
    {
        return BlockRenderType.MODEL;
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit)
    {
        if (world.getBlockEntity(pos) instanceof RoomSignBlockEntity blockEntity)
        {
            player.openHandledScreen(blockEntity);
        }

        return ActionResult.SUCCESS;
    }
}
