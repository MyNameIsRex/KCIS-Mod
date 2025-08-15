package data.psychologytheory.kcisdeco.blocks;

import data.psychologytheory.kcisdeco.blocks.blockentities.CabinetBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class CabinetBlock extends StorageBlock
{
    public CabinetBlock(Settings settings)
    {
        super(settings);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state)
    {
        return new CabinetBlockEntity(pos, state);
    }
}
