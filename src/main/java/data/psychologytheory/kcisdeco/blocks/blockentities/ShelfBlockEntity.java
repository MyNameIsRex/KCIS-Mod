package data.psychologytheory.kcisdeco.blocks.blockentities;

import net.minecraft.block.BlockState;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public class ShelfBlockEntity extends StorageBlockEntity
{
    public ShelfBlockEntity(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.SHELF_BE, pos, state);
    }

    @Override
    public Text getDisplayName()
    {
        return Text.translatable("screen.kcisdeco.shelf");
    }
}
