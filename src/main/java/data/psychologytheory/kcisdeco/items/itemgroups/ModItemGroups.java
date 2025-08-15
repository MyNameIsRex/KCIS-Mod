package data.psychologytheory.kcisdeco.items.itemgroups;

import data.psychologytheory.kcisdeco.KCISDecorationMod;
import data.psychologytheory.kcisdeco.blocks.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups
{

   public static final ItemGroup BUILDING_ESSENTIALS_ITEM_GROUP = Registry.register(Registries.ITEM_GROUP,
           Identifier.of(KCISDecorationMod.MOD_ID, "building_essentials"),
           FabricItemGroup.builder()
                   .icon(() -> new ItemStack(ModBlocks.FRAMED_WINDOW_DISCONNECTED.asItem()))
                   .displayName(Text.translatable("itemGroup.kcisdeco.building_essentials"))
                   .entries(((displayContext, entries) ->
                   {
                      entries.add(ModBlocks.FRAMED_WINDOW_DISCONNECTED);
                      entries.add(ModBlocks.FRAMED_WINDOW_DISCONNECTED_FOGGED);
                      entries.add(ModBlocks.FRAMED_WINDOW_DOUBLE);
                      entries.add(ModBlocks.FRAMED_WINDOW_DOUBLE_FOGGED);
                      entries.add(ModBlocks.FRAMED_WINDOW_EXTENDABLE);
                      entries.add(ModBlocks.CEILING_PANEL_BOTTOM);
                      entries.add(ModBlocks.CEILING_PANEL_TOP);
                      entries.add(ModBlocks.FLORESCENT_LIGHT_FLUSH);
                   })).build());
    public static final ItemGroup CLASSROOM_ITEM_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(KCISDecorationMod.MOD_ID, "classroom"),
            FabricItemGroup.builder()
                    .icon(() -> new ItemStack(ModBlocks.YELLOW_ROOM_SIGN.asItem()))
                    .displayName(Text.translatable("itemGroup.kcisdeco.classroom"))
                    .entries(((displayContext, entries) ->
                    {
                       entries.add(ModBlocks.RED_ROOM_SIGN);
                       entries.add(ModBlocks.ORANGE_ROOM_SIGN);
                       entries.add(ModBlocks.YELLOW_ROOM_SIGN);
                       entries.add(ModBlocks.GREEN_ROOM_SIGN);
                       entries.add(ModBlocks.BLUE_ROOM_SIGN);
                       entries.add(ModBlocks.CLASSROOM_DOOR);
                       entries.add(ModBlocks.STUDENT_DESK);
                       entries.add(ModBlocks.STUDENT_SEAT);
                       entries.add(ModBlocks.TEACHER_DESK);
                       entries.add(ModBlocks.TEACHER_SEAT);
                       entries.add(ModBlocks.WHITE_BOARD);
                       entries.add(ModBlocks.BULLETIN_BOARD);
                       entries.add(ModBlocks.CABINET);
                       entries.add(ModBlocks.LOCKER);
                       entries.add(ModBlocks.SHELF);
                       entries.add(ModBlocks.CEILING_PANEL_TOP);
                       entries.add(ModBlocks.FLORESCENT_LIGHT_FLUSH);
                       entries.add(ModBlocks.FLORESCENT_LIGHT_DOUBLE);
                       entries.add(ModBlocks.FLORESCENT_LIGHT_DOUBLE_FENCED);
                    }))
                    .build());

    public static void registerItemGroups() {}
}
