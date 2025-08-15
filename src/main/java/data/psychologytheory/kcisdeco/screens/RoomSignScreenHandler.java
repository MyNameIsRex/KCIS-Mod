package data.psychologytheory.kcisdeco.screens;

import data.psychologytheory.kcisdeco.blocks.blockentities.RoomSignBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.math.BlockPos;

public class RoomSignScreenHandler extends ScreenHandler
{
    private final RoomSignBlockEntity thisBlockEntity;

    public RoomSignScreenHandler(int syncId, PlayerInventory playerInventory, BlockPos pos)
    {
        this(syncId, playerInventory, playerInventory.player.getWorld().getBlockEntity(pos));
    }

    public RoomSignScreenHandler(int syncId, PlayerInventory playerInventory, BlockEntity blockEntity)
    {
        super(ModScreenHandlers.ROOM_SIGN_SCREEN_HANDLER, syncId);
        this.thisBlockEntity = (RoomSignBlockEntity) blockEntity;

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot)
    {
        return null;
    }

    @Override
    public boolean canUse(PlayerEntity player)
    {
        return player.getInventory().canPlayerUse(player);
    }

    private void addPlayerInventory(PlayerInventory playerInventory)
    {
        for (int row = 0; row < 3; ++row)
        {
            for (int col = 0; col < 9; ++col)
            {
                this.addSlot(new Slot(playerInventory, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
            }
        }
    }

    private void addPlayerHotbar(PlayerInventory playerInventory)
    {
        for (int slot = 0; slot < 9; slot++)
        {
            this.addSlot(new Slot(playerInventory, slot, 8 + slot * 18, 142));
        }
    }

    public RoomSignBlockEntity getBlockEntity()
    {
        return this.thisBlockEntity;
    }
}
