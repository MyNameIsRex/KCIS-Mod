package data.psychologytheory.kcisdeco.blocks;

import com.mojang.serialization.MapCodec;
import data.psychologytheory.kcisdeco.entities.ModEntities;
import data.psychologytheory.kcisdeco.entities.SittableEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SittableBlock extends HorizontalFacingBlock
{
    public static final MapCodec<SittableBlock> CODEC = createCodec(SittableBlock::new);

    private static final VoxelShape SHAPE_NS = createCuboidShape(2, 0, 1, 14, 7, 15);
    private static final VoxelShape SHAPE_EW = createCuboidShape(1, 0, 2, 15, 7, 14);

    public SittableBlock(Settings settings)
    {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(FACING, Direction.NORTH));
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
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit)
    {
        if (!world.isClient)
        {
            Entity e;
            List<SittableEntity> entities = world.getEntitiesByType(ModEntities.SITTABLE_ENTITY, new Box(pos), sittableEntity -> true);

            if (!entities.isEmpty())
            {
                e = entities.getFirst();
                player.startRiding(e);
                return ActionResult.SUCCESS;
            }

            e = ModEntities.SITTABLE_ENTITY.spawn((ServerWorld) world, pos, SpawnReason.TRIGGERED);
            player.startRiding(e);
            return ActionResult.SUCCESS;
        }

        return ActionResult.SUCCESS;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder)
    {
        builder.add(FACING);
    }
}
