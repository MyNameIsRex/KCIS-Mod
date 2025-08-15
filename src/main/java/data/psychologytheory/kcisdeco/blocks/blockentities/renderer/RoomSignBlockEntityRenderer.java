package data.psychologytheory.kcisdeco.blocks.blockentities.renderer;

import data.psychologytheory.kcisdeco.blocks.ModBlocks;
import data.psychologytheory.kcisdeco.blocks.RoomSignBlock;
import data.psychologytheory.kcisdeco.blocks.blockentities.RoomSignBlockEntity;
import data.psychologytheory.kcisdeco.networking.packets.payload.RoomSignPayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Colors;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

public class RoomSignBlockEntityRenderer implements BlockEntityRenderer<RoomSignBlockEntity>
{
    private final BlockEntityRendererFactory.Context context;
    private String[] contents;

    public RoomSignBlockEntityRenderer(BlockEntityRendererFactory.Context context)
    {
        this.context = context;
    }

    @Override
    public void render(RoomSignBlockEntity entity,
                       float tickProgress,
                       MatrixStack matrices,
                       VertexConsumerProvider vertexConsumers,
                       int light,
                       int overlay,
                       Vec3d cameraPos)
    {
        TextRenderer textRenderer = context.getTextRenderer();
        BlockState blockState = entity.getWorld().getBlockState(entity.getPos());

        if (blockState.getBlock() != ModBlocks.RED_ROOM_SIGN
                && blockState.getBlock() != ModBlocks.ORANGE_ROOM_SIGN
                && blockState.getBlock() != ModBlocks.YELLOW_ROOM_SIGN
                && blockState.getBlock() != ModBlocks.GREEN_ROOM_SIGN
                && blockState.getBlock() != ModBlocks.BLUE_ROOM_SIGN) return;

        switch (blockState.get(RoomSignBlock.FACING))
        {
            case SOUTH ->
            {
                drawRoomNumber(blockState, entity, matrices, textRenderer, vertexConsumers, light, -0.025F, -0.525F, 0.57F, 180F);
                drawRoomNumber(blockState, entity, matrices, textRenderer, vertexConsumers, light, -0.2F, -0.525F, 0.43F, 0F);

                drawSubject(blockState, entity, matrices, textRenderer, vertexConsumers, light, -0.1F, -0.35F, 0.57F, 180F);
                drawSubject(blockState, entity, matrices, textRenderer, vertexConsumers, light, -0.46F, -0.35F, 0.43F, 0F);

                drawTeacherName(blockState, entity, matrices, textRenderer, vertexConsumers, light, -0.1F, -0.28F, 0.57F, 180F);
                drawTeacherName(blockState, entity, matrices, textRenderer, vertexConsumers, light, -0.46F, -0.28F, 0.43F, 0F);
            }
            case EAST ->
            {
                drawRoomNumber(blockState, entity, matrices, textRenderer, vertexConsumers, light, -0.57F, -0.525F, 0.975F, 90F);
                drawRoomNumber(blockState, entity, matrices, textRenderer, vertexConsumers, light, -0.43F, -0.525F, 0.8F, 270F);

                drawSubject(blockState, entity, matrices, textRenderer, vertexConsumers, light, -0.57F, -0.35F, 0.9F, 90F);
                drawSubject(blockState, entity, matrices, textRenderer, vertexConsumers, light, -0.43F, -0.35F, 0.54F, 270F);

                drawTeacherName(blockState, entity, matrices, textRenderer, vertexConsumers, light, -0.57F, -0.28F, 0.9F, 90F);
                drawTeacherName(blockState, entity, matrices, textRenderer, vertexConsumers, light, -0.43F, -0.28F, 0.54F, 270F);
            }
            case WEST ->
            {
                drawRoomNumber(blockState, entity, matrices, textRenderer, vertexConsumers, light, -0.43F, -0.525F, 0.025F, 270F);
                drawRoomNumber(blockState, entity, matrices, textRenderer, vertexConsumers, light, -0.57F, -0.525F, 0.2F, 90F);

                drawSubject(blockState, entity, matrices, textRenderer, vertexConsumers, light, -0.43F, -0.35F, 0.1F, 270F);
                drawSubject(blockState, entity, matrices, textRenderer, vertexConsumers, light, -0.57F, -0.35F, 0.46F, 90F);

                drawTeacherName(blockState, entity, matrices, textRenderer, vertexConsumers, light, -0.43F, -0.28F, 0.1F, 270F);
                drawTeacherName(blockState, entity, matrices, textRenderer, vertexConsumers, light, -0.57F, -0.28F, 0.46F, 90F);
            }

            default ->
            {
                drawRoomNumber(blockState, entity, matrices, textRenderer, vertexConsumers, light, -0.975F, -0.525F, 0.43F, 0F);
                drawRoomNumber(blockState, entity, matrices, textRenderer, vertexConsumers, light, -0.8F, -0.525F, 0.57F, 180F);

                drawSubject(blockState, entity, matrices, textRenderer, vertexConsumers, light, -0.9F, -0.35F, 0.43F, 0F);
                drawSubject(blockState, entity, matrices, textRenderer, vertexConsumers, light, -0.54F, -0.35F, 0.57F, 180F);

                drawTeacherName(blockState, entity, matrices, textRenderer, vertexConsumers, light, -0.9F, -0.28F, 0.43F, 0F);
                drawTeacherName(blockState, entity, matrices, textRenderer, vertexConsumers, light, -0.54F, -0.28F, 0.57F, 180F);
            }
        }
    }

    private static int getRoomNumberColor(BlockState blockState)
    {
        int roomNumberColor = Colors.WHITE;
        if (blockState.getBlock().equals(ModBlocks.RED_ROOM_SIGN)) roomNumberColor = Colors.RED;
        if (blockState.getBlock().equals(ModBlocks.ORANGE_ROOM_SIGN)) roomNumberColor = Colors.LIGHT_RED;
        if (blockState.getBlock().equals(ModBlocks.YELLOW_ROOM_SIGN)) roomNumberColor = Colors.YELLOW;
        if (blockState.getBlock().equals(ModBlocks.GREEN_ROOM_SIGN)) roomNumberColor = Colors.GREEN;
        if (blockState.getBlock().equals(ModBlocks.BLUE_ROOM_SIGN)) roomNumberColor = Colors.BLUE;
        return roomNumberColor;
    }

    private static void drawRoomNumber(BlockState blockState, RoomSignBlockEntity entity,
                                       MatrixStack matrices,
                                       TextRenderer textRenderer,
                                       VertexConsumerProvider vertexConsumers,
                                       int light,
                                       float x,
                                       float y,
                                       float z,
                                       float yRotation)
    {
        int roomNumberColor = getRoomNumberColor(blockState);

        matrices.push();

        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(180));
        matrices.translate(x, y, z);
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(yRotation));
        matrices.scale(0.0075F, 0.0075F, 0.0075F);

        textRenderer.draw(entity.getRoomNumber(),
                0,
                0,
                roomNumberColor,
                false,
                matrices.peek().getPositionMatrix(),
                vertexConsumers,
                TextRenderer.TextLayerType.NORMAL,
                0,
                light);
        matrices.pop();
    }

    private static void drawSubject(BlockState blockState, RoomSignBlockEntity entity,
                                       MatrixStack matrices,
                                       TextRenderer textRenderer,
                                       VertexConsumerProvider vertexConsumers,
                                       int light,
                                       float x,
                                       float y,
                                       float z,
                                       float yRotation)
    {
        int subjectColor = Colors.BLACK;

        matrices.push();

        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(180));
        matrices.translate(x, y, z);
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(yRotation));
        matrices.scale(0.0045F, 0.0045F, 0.0045F);

        textRenderer.draw(entity.getSubject(),
                0,
                0,
                subjectColor,
                false,
                matrices.peek().getPositionMatrix(),
                vertexConsumers,
                TextRenderer.TextLayerType.NORMAL,
                0,
                light);
        matrices.pop();
    }

    private static void drawTeacherName(BlockState blockState, RoomSignBlockEntity entity,
                                    MatrixStack matrices,
                                    TextRenderer textRenderer,
                                    VertexConsumerProvider vertexConsumers,
                                    int light,
                                    float x,
                                    float y,
                                    float z,
                                    float yRotation)
    {
        int teacherNameColor = Colors.RED;

        matrices.push();

        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(180));
        matrices.translate(x, y, z);
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(yRotation));
        matrices.scale(0.005F, 0.005F, 0.005F);

        textRenderer.draw(entity.getTeacher(),
                0,
                0,
                teacherNameColor,
                false,
                matrices.peek().getPositionMatrix(),
                vertexConsumers,
                TextRenderer.TextLayerType.NORMAL,
                0,
                light);
        matrices.pop();
    }
}
