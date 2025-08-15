package data.psychologytheory.kcisdeco.blocks.blockentities;

import data.psychologytheory.kcisdeco.blocks.blockentities.renderer.RoomSignBlockEntityRenderer;
import data.psychologytheory.kcisdeco.networking.packets.payload.RoomSignPayload;
import data.psychologytheory.kcisdeco.screens.RoomSignScreenHandler;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class RoomSignBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory<BlockPos>
{
    private String roomNumber;
    private String subject;
    private String teacher;

    public RoomSignBlockEntity(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.ROOM_SIGN_BE, pos, state);
        this.roomNumber = "1304";
        this.subject = "Chinese";
        this.teacher = "Celeste Huang";
    }

    @Override
    protected void writeData(WriteView view)
    {
        super.writeData(view);

        view.putString("room_number", this.roomNumber);
        view.putString("subject", this.subject);
        view.putString("teacher", this.teacher);
    }

    @Override
    protected void readData(ReadView view)
    {
        super.readData(view);

        this.roomNumber = view.getString("room_number", "XXXX");
        this.subject = view.getString("subject", "Foobar");
        this.teacher = view.getString("teacher", "Lorem Ipsum");
    }

    @Override
    public @Nullable Packet<ClientPlayPacketListener> toUpdatePacket()
    {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registries)
    {
        return createNbt(registries);
    }

    @Override
    public BlockPos getScreenOpeningData(ServerPlayerEntity serverPlayerEntity)
    {
        return this.pos;
    }

    @Override
    public Text getDisplayName()
    {
        return Text.translatable("screen.kcisdeco.room_sign");
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player)
    {
        return new RoomSignScreenHandler(syncId, playerInventory, this.pos);
    }

    public void handleIncomingContent(String string)
    {
        String[] contents = string.split("\\|");
        if (contents.length == 0) return;
        this.setRoomNumber(contents[0]);

        if (contents.length == 1) return;
        this.setSubject(contents[1]);

        if (contents.length == 2) return;
        this.setTeacher(contents[2]);
    }

    public String getRoomNumber()
    {
        return this.roomNumber;
    }

    public String getSubject()
    {
        return this.subject;
    }

    public String getTeacher()
    {
        return this.teacher;
    }

    public void setRoomNumber(String roomNumber)
    {
        this.roomNumber = roomNumber;
    }

    public void setSubject(String subject)
    {
        this.subject = subject;
    }

    public void setTeacher(String teacher)
    {
        this.teacher = teacher;
    }
}
