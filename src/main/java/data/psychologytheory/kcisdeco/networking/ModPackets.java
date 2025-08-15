package data.psychologytheory.kcisdeco.networking;


import data.psychologytheory.kcisdeco.KCISDecorationMod;
import data.psychologytheory.kcisdeco.blocks.blockentities.RoomSignBlockEntity;
import data.psychologytheory.kcisdeco.networking.packets.payload.RoomSignPayload;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.Identifier;

public class ModPackets
{
    public static final Identifier CHANGE_ROOM_SIGN_ID = Identifier.of(KCISDecorationMod.MOD_ID, "change_room_sign");

    public static void registerCommon()
    {
        PayloadTypeRegistry.playC2S().register(RoomSignPayload.ID, RoomSignPayload.CODEC);
    }

    public static void registerS2CPackets() {}

    public static void registerC2SPackets()
    {
        ServerPlayNetworking.registerGlobalReceiver(RoomSignPayload.ID, (payload, context) ->
        {
            BlockEntity blockEntity = context.player().getWorld().getBlockEntity(payload.blockPos());

            if (blockEntity instanceof RoomSignBlockEntity)
            {
                ((RoomSignBlockEntity) blockEntity).handleIncomingContent(payload.string());
                blockEntity.markDirty();
            }
        });
    }
}
