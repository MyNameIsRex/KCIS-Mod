package data.psychologytheory.kcisdeco.screens;

import data.psychologytheory.kcisdeco.KCISDecorationMod;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class ModScreenHandlers
{
    public static final ScreenHandlerType<RoomSignScreenHandler> ROOM_SIGN_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(KCISDecorationMod.MOD_ID, "room_sign_screen_handler"),
                    new ExtendedScreenHandlerType<>(RoomSignScreenHandler::new, BlockPos.PACKET_CODEC));

    public static void registerScreenHandlers() {}
}
