package data.psychologytheory.kcisdeco.blocks.blockentities;

import data.psychologytheory.kcisdeco.KCISDecorationMod;
import data.psychologytheory.kcisdeco.blocks.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities
{
    public static final BlockEntityType<RoomSignBlockEntity> ROOM_SIGN_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(KCISDecorationMod.MOD_ID, "room_sign_be"),
                    FabricBlockEntityTypeBuilder.create(RoomSignBlockEntity::new, ModBlocks.RED_ROOM_SIGN, ModBlocks.ORANGE_ROOM_SIGN, ModBlocks.YELLOW_ROOM_SIGN, ModBlocks.GREEN_ROOM_SIGN, ModBlocks.BLUE_ROOM_SIGN).build());
    public static final BlockEntityType<CabinetBlockEntity> CABINET_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(KCISDecorationMod.MOD_ID, "cabinet_be"),
                    FabricBlockEntityTypeBuilder.create(CabinetBlockEntity::new, ModBlocks.CABINET).build());
    public static final BlockEntityType<LockerBlockEntity> LOCKER_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(KCISDecorationMod.MOD_ID, "locker_be"),
                    FabricBlockEntityTypeBuilder.create(LockerBlockEntity::new, ModBlocks.LOCKER).build());
    public static final BlockEntityType<ShelfBlockEntity> SHELF_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(KCISDecorationMod.MOD_ID, "shelf_be"),
                    FabricBlockEntityTypeBuilder.create(ShelfBlockEntity::new, ModBlocks.SHELF).build());

    public static void registerModBlockEntities() {}
}
