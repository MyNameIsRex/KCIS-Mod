package data.psychologytheory.kcisdeco.blocks;

import data.psychologytheory.kcisdeco.KCISDecorationMod;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModBlocks
{
    //Signs
    public static final Block RED_ROOM_SIGN = registerBlock("red_room_sign",
            (properties) -> new RoomSignBlock(properties.nonOpaque()));
    public static final Block ORANGE_ROOM_SIGN = registerBlock("orange_room_sign",
            (properties) -> new RoomSignBlock(properties.nonOpaque()));
    public static final Block YELLOW_ROOM_SIGN = registerBlock("yellow_room_sign",
            (properties) -> new RoomSignBlock(properties.nonOpaque()));
    public static final Block GREEN_ROOM_SIGN = registerBlock("green_room_sign",
            (properties) -> new RoomSignBlock(properties.nonOpaque()));
    public static final Block BLUE_ROOM_SIGN = registerBlock("blue_room_sign",
            (properties) -> new RoomSignBlock(properties.nonOpaque()));

    //Doors
    public static final Block WINDOWED_DOOR = registerBlock("windowed_door",
            (properties) -> new CenteredDoorBlock(BlockSetType.WARPED, properties.nonOpaque()));

    //Windows
    public static final Block FRAMED_WINDOW_DISCONNECTED = registerBlock("framed_window_disconnected",
            (properties) -> new FramedWindowBlock(properties.nonOpaque()));
    public static final Block FRAMED_WINDOW_DISCONNECTED_FOGGED = registerBlock("framed_window_disconnected_fogged",
            (properties) -> new FramedWindowBlock(properties.nonOpaque()));
    public static final Block FRAMED_WINDOW_DOUBLE = registerBlock("framed_window_double",
            (properties) -> new FramedWindowDoubleBlock(properties.nonOpaque()));
    public static final Block FRAMED_WINDOW_DOUBLE_FOGGED = registerBlock("framed_window_double_fogged",
            (properties) -> new FramedWindowDoubleBlock(properties.nonOpaque()));
    public static final Block FRAMED_WINDOW_EXTENDABLE = registerBlock("framed_window_extendable",
            (properties) -> new FramedWindowExtendableBlock(properties.nonOpaque()));

    //Furniture
    public static final Block STUDENT_DESK = registerBlock("student_desk",
            (properties) -> new StudentDeskBlock(properties.nonOpaque()));
    public static final Block STUDENT_SEAT = registerBlock("student_seat",
            (properties) -> new SittableBlock(properties.nonOpaque()));
    public static final Block TEACHER_DESK = registerBlock("teacher_desk",
            (properties) -> new TeacherDeskBlock(properties.nonOpaque()));
    public static final Block TEACHER_SEAT = registerBlock("teacher_seat",
            (properties) -> new SittableBlock(properties.nonOpaque()));
    public static final Block WHITE_BOARD = registerBlock("whiteboard",
            (properties) -> new BoardBlock(properties.nonOpaque()));
    public static final Block BULLETIN_BOARD = registerBlock("bulletin_board",
            (properties) -> new BoardBlock(properties.nonOpaque()));
    public static final Block CABINET = registerBlock("cabinet",
            (properties) -> new CabinetBlock(properties.nonOpaque()));
    public static final Block LOCKER = registerBlock("locker",
            (properties) -> new LockerBlock(properties.nonOpaque()));
    public static final Block SHELF = registerBlock("shelf",
            (properties) -> new ShelfBlock(properties.nonOpaque()));

    //Ceiling Panel
    public static final Block CEILING_PANEL_BOTTOM = registerBlock("ceiling_panel_bottom",
            (properties) -> new CeilingPanelBlock(properties.nonOpaque(), false));
    public static final Block CEILING_PANEL_TOP = registerBlock("ceiling_panel_top",
            (properties) -> new CeilingPanelBlock(properties.nonOpaque(), true));

    //Light
    public static final Block FLORESCENT_LIGHT_FLUSH = registerBlock("florescent_light_flush",
            (properties) -> new FlorescentLightBlock(properties.nonOpaque().luminance((state) -> 15)));
    public static final Block FLORESCENT_LIGHT_DOUBLE = registerBlock("florescent_light_double",
            (properties) -> new FlorescentLightDoubleBlock(properties.nonOpaque().luminance((state) -> 15)));
    public static final Block FLORESCENT_LIGHT_DOUBLE_FENCED = registerBlock("florescent_light_double_fenced",
            (properties) -> new FlorescentLightDoubleBlock(properties.nonOpaque().luminance((state) -> 15)));

    private static Block registerBlock(String name, Function<AbstractBlock.Settings, Block> function)
    {
        Block block = function.apply(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(KCISDecorationMod.MOD_ID, name))));
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(KCISDecorationMod.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block)
    {
        Registry.register(Registries.ITEM, Identifier.of(KCISDecorationMod.MOD_ID, name), new BlockItem(block, new Item.Settings().useBlockPrefixedTranslationKey().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(KCISDecorationMod.MOD_ID, name)))));
    }

    public static void registerModBlocks() {}
}
