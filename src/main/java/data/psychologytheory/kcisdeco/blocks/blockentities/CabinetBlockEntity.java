package data.psychologytheory.kcisdeco.blocks.blockentities;

import net.minecraft.block.BlockState;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public class CabinetBlockEntity extends StorageBlockEntity
{
    public CabinetBlockEntity(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.CABINET_BE, pos, state);
    }

    @Override
    public Text getDisplayName()
    {
        return Text.translatable("screen.kcisdeco.cabinet");
    }
}
