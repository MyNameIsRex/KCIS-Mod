package data.psychologytheory.kcisdeco.blocks.blockentities;

import net.minecraft.block.BlockState;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public class LockerBlockEntity extends StorageBlockEntity
{
    public LockerBlockEntity(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.LOCKER_BE, pos, state);
    }

    @Override
    public Text getDisplayName()
    {
        return Text.translatable("screen.kcisdeco.locker");
    }
}
