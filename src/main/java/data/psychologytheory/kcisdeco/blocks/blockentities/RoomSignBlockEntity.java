package data.psychologytheory.kcisdeco.blocks.blockentities;

import data.psychologytheory.kcisdeco.screens.RoomSignScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class RoomSignBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory<BlockPos>
{
    private String roomNumber;
    private String subjectLine1;
    private String subjectLine2;
    private String teacherLine1;
    private String teacherLine2;

    public RoomSignBlockEntity(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.ROOM_SIGN_BE, pos, state);
        this.roomNumber = "1304";
        this.subjectLine1 = " ";
        this.subjectLine2 = "Chinese";
        this.teacherLine1 = "Celeste Huang";
        this.teacherLine2 = " ";
    }

    @Override
    protected void writeData(WriteView view)
    {
        super.writeData(view);

        view.putString("room_number", this.roomNumber);
        view.putString("subjectLine1", this.subjectLine1);
        view.putString("subjectLine2", this.subjectLine2);
        view.putString("teacherLine1", this.teacherLine1);
        view.putString("teacherLine2", this.teacherLine2);
    }

    @Override
    protected void readData(ReadView view)
    {
        super.readData(view);

        this.roomNumber = view.getString("room_number", "XXXX");
        this.subjectLine1 = view.getString("subjectLine1", "Foo");
        this.subjectLine2 = view.getString("subjectLine2", "Bar");
        this.teacherLine1 = view.getString("teacherLine1", "Lorem");
        this.teacherLine2 = view.getString("teacherLine2", "Ipsum");
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
        this.setSubjectLine1(contents[1]);

        if (contents.length == 2) return;
        this.setSubjectLine2(contents[2]);

        if (contents.length == 3) return;
        this.setTeacherLine1(contents[3]);

        if (contents.length == 4) return;
        this.setTeacherLine2(contents[4]);
    }

    public String getRoomNumber()
    {
        return this.roomNumber;
    }

    public String getSubjectLine1()
    {
        return this.subjectLine1;
    }

    public String getSubjectLine2()
    {
        return this.subjectLine2;
    }

    public String getTeacherLine1()
    {
        return this.teacherLine1;
    }

    public String getTeacherLine2()
    {
        return this.teacherLine2;
    }

    public void setRoomNumber(String roomNumber)
    {
        this.roomNumber = roomNumber;
    }

    public void setSubjectLine1(String subjectLine1)
    {
        this.subjectLine1 = subjectLine1;
    }

    public void setSubjectLine2(String subjectLine2)
    {
        this.subjectLine2 = subjectLine2;
    }

    public void setTeacherLine1(String teacherLine1)
    {
        this.teacherLine1 = teacherLine1;
    }

    public void setTeacherLine2(String teacherLine2)
    {
        this.teacherLine2 = teacherLine2;
    }
}
