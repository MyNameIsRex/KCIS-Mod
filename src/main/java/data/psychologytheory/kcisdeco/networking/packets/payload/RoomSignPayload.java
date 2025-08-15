package data.psychologytheory.kcisdeco.networking.packets.payload;

import data.psychologytheory.kcisdeco.networking.ModPackets;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.math.BlockPos;

public record RoomSignPayload(String string, BlockPos blockPos) implements CustomPayload
{
    public static final CustomPayload.Id<RoomSignPayload> ID = new CustomPayload.Id<>(ModPackets.CHANGE_ROOM_SIGN_ID);
    public static final PacketCodec<RegistryByteBuf, RoomSignPayload> CODEC =
            PacketCodec.tuple(PacketCodecs.STRING, RoomSignPayload::string, BlockPos.PACKET_CODEC, RoomSignPayload::blockPos, RoomSignPayload::new);

    @Override
    public Id<? extends CustomPayload> getId()
    {
        return ID;
    }
}
