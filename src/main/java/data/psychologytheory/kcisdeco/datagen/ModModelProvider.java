package data.psychologytheory.kcisdeco.datagen;

import data.psychologytheory.kcisdeco.blocks.ModBlocks;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;

public class ModModelProvider extends FabricModelProvider
{
    public ModModelProvider(FabricDataOutput output)
    {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator)
    {
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.YELLOW_ROOM_SIGN);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.RED_ROOM_SIGN);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.ORANGE_ROOM_SIGN);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.GREEN_ROOM_SIGN);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.BLUE_ROOM_SIGN);

        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.FRAMED_WINDOW_DISCONNECTED);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.FRAMED_WINDOW_DISCONNECTED_FOGGED);

        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.STUDENT_DESK);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.STUDENT_SEAT);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.TEACHER_SEAT);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.CABINET);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.LOCKER);

        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.FLORESCENT_LIGHT_FLUSH);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator)
    {

    }
}
