package data.psychologytheory.kcisdeco;

import data.psychologytheory.kcisdeco.blocks.ModBlocks;
import data.psychologytheory.kcisdeco.blocks.blockentities.ModBlockEntities;
import data.psychologytheory.kcisdeco.blocks.blockentities.renderer.RoomSignBlockEntityRenderer;
import data.psychologytheory.kcisdeco.entities.ModEntities;
import data.psychologytheory.kcisdeco.entities.renderer.SittableEntityRenderer;
import data.psychologytheory.kcisdeco.networking.ModPackets;
import data.psychologytheory.kcisdeco.screens.ModScreenHandlers;
import data.psychologytheory.kcisdeco.screens.RoomSignScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.BlockRenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

public class KCISDecorationModClient implements ClientModInitializer
{
    @Override
    public void onInitializeClient()
    {
        BlockRenderLayerMap.putBlock(ModBlocks.FRAMED_WINDOW_DISCONNECTED, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.FRAMED_WINDOW_DISCONNECTED_FOGGED, BlockRenderLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(ModBlocks.FRAMED_WINDOW_DOUBLE, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.FRAMED_WINDOW_DOUBLE_FOGGED, BlockRenderLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(ModBlocks.FRAMED_WINDOW_EXTENDABLE, BlockRenderLayer.CUTOUT);

        BlockRenderLayerMap.putBlock(ModBlocks.WINDOWED_DOOR, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.GLASSED_DOOR, BlockRenderLayer.CUTOUT);

        EntityRendererRegistry.register(ModEntities.SITTABLE_ENTITY, SittableEntityRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.ROOM_SIGN_BE, RoomSignBlockEntityRenderer::new);
        HandledScreens.register(ModScreenHandlers.ROOM_SIGN_SCREEN_HANDLER, RoomSignScreen::new);
        ModPackets.registerS2CPackets();
    }
}
