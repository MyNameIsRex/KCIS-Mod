package data.psychologytheory.kcisdeco.blocks.blockentities.renderer;

import data.psychologytheory.kcisdeco.blocks.ModBlocks;
import data.psychologytheory.kcisdeco.blocks.RoomSignBlock;
import data.psychologytheory.kcisdeco.blocks.blockentities.RoomSignBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Colors;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;

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
                drawRoomNumberFromLeft(blockState, entity, matrices, textRenderer, vertexConsumers, light, -0.025F, -0.525F, 0.57F, 180F);
                drawRoomNumberFromRight(blockState, entity, matrices, textRenderer, vertexConsumers, light, -0.025F, -0.525F, 0.43F, 0F);

                drawSubjectLine1(blockState, entity, matrices, textRenderer, vertexConsumers, light, -0.1F, -0.395F, 0.57F, 180F);
                drawSubjectLine1(blockState, entity, matrices, textRenderer, vertexConsumers, light, -0.46F, -0.395F, 0.43F, 0F);

                drawSubjectLine2(blockState, entity, matrices, textRenderer, vertexConsumers, light, -0.1F, -0.35F, 0.57F, 180F);
                drawSubjectLine2(blockState, entity, matrices, textRenderer, vertexConsumers, light, -0.46F, -0.35F, 0.43F, 0F);

                drawTeacherNameLine1(blockState, entity, matrices, textRenderer, vertexConsumers, light, -0.1F, -0.28F, 0.57F, 180F);
                drawTeacherNameLine1(blockState, entity, matrices, textRenderer, vertexConsumers, light, -0.46F, -0.28F, 0.43F, 0F);

                drawTeacherNameLine2(blockState, entity, matrices, textRenderer, vertexConsumers, light, -0.1F, -0.23F, 0.57F, 180F);
                drawTeacherNameLine2(blockState, entity, matrices, textRenderer, vertexConsumers, light, -0.46F, -0.23F, 0.43F, 0F);
            }
            case EAST ->
            {
                drawRoomNumberFromLeft(blockState, entity, matrices, textRenderer, vertexConsumers, light, -0.57F, -0.525F, 0.975F, 90F);
                drawRoomNumberFromRight(blockState, entity, matrices, textRenderer, vertexConsumers, light, -0.43F, -0.525F, 0.975F, 270F);

                drawSubjectLine1(blockState, entity, matrices, textRenderer, vertexConsumers, light, -0.57F, -0.395F, 0.9F, 90F);
                drawSubjectLine1(blockState, entity, matrices, textRenderer, vertexConsumers, light, -0.43F, -0.395F, 0.54F, 270F);

                drawSubjectLine2(blockState, entity, matrices, textRenderer, vertexConsumers, light, -0.57F, -0.35F, 0.9F, 90F);
                drawSubjectLine2(blockState, entity, matrices, textRenderer, vertexConsumers, light, -0.43F, -0.35F, 0.54F, 270F);

                drawTeacherNameLine1(blockState, entity, matrices, textRenderer, vertexConsumers, light, -0.57F, -0.28F, 0.9F, 90F);
                drawTeacherNameLine1(blockState, entity, matrices, textRenderer, vertexConsumers, light, -0.43F, -0.28F, 0.54F, 270F);

                drawTeacherNameLine2(blockState, entity, matrices, textRenderer, vertexConsumers, light, -0.57F, -0.23F, 0.9F, 90F);
                drawTeacherNameLine2(blockState, entity, matrices, textRenderer, vertexConsumers, light, -0.43F, -0.23F, 0.54F, 270F);
            }
            case WEST ->
            {
                drawRoomNumberFromLeft(blockState, entity, matrices, textRenderer, vertexConsumers, light, -0.43F, -0.525F, 0.025F, 270F);
                drawRoomNumberFromRight(blockState, entity, matrices, textRenderer, vertexConsumers, light, -0.57F, -0.525F, 0.025F, 90F);

                drawSubjectLine1(blockState, entity, matrices, textRenderer, vertexConsumers, light, -0.43F, -0.395F, 0.1F, 270F);
                drawSubjectLine1(blockState, entity, matrices, textRenderer, vertexConsumers, light, -0.57F, -0.395F, 0.46F, 90F);

                drawSubjectLine2(blockState, entity, matrices, textRenderer, vertexConsumers, light, -0.43F, -0.35F, 0.1F, 270F);
                drawSubjectLine2(blockState, entity, matrices, textRenderer, vertexConsumers, light, -0.57F, -0.35F, 0.46F, 90F);

                drawTeacherNameLine1(blockState, entity, matrices, textRenderer, vertexConsumers, light, -0.43F, -0.28F, 0.1F, 270F);
                drawTeacherNameLine1(blockState, entity, matrices, textRenderer, vertexConsumers, light, -0.57F, -0.28F, 0.46F, 90F);

                drawTeacherNameLine2(blockState, entity, matrices, textRenderer, vertexConsumers, light, -0.43F, -0.23F, 0.1F, 270F);
                drawTeacherNameLine2(blockState, entity, matrices, textRenderer, vertexConsumers, light, -0.57F, -0.23F, 0.46F, 90F);
            }

            default ->
            {
                drawRoomNumberFromLeft(blockState, entity, matrices, textRenderer, vertexConsumers, light, -0.975F, -0.525F, 0.43F, 0F);
                drawRoomNumberFromRight(blockState, entity, matrices, textRenderer, vertexConsumers, light, -0.975F, -0.525F, 0.57F, 180F);

                drawSubjectLine1(blockState, entity, matrices, textRenderer, vertexConsumers, light, -0.9F, -0.395F, 0.43F, 0F);
                drawSubjectLine1(blockState, entity, matrices, textRenderer, vertexConsumers, light, -0.54F, -0.395F, 0.57F, 180F);

                drawSubjectLine2(blockState, entity, matrices, textRenderer, vertexConsumers, light, -0.9F, -0.35F, 0.43F, 0F);
                drawSubjectLine2(blockState, entity, matrices, textRenderer, vertexConsumers, light, -0.54F, -0.35F, 0.57F, 180F);

                drawTeacherNameLine1(blockState, entity, matrices, textRenderer, vertexConsumers, light, -0.9F, -0.28F, 0.43F, 0F);
                drawTeacherNameLine1(blockState, entity, matrices, textRenderer, vertexConsumers, light, -0.54F, -0.28F, 0.57F, 180F);

                drawTeacherNameLine2(blockState, entity, matrices, textRenderer, vertexConsumers, light, -0.9F, -0.23F, 0.43F, 0F);
                drawTeacherNameLine2(blockState, entity, matrices, textRenderer, vertexConsumers, light, -0.54F, -0.23F, 0.57F, 180F);
            }
        }
    }

    private static int getRoomNumberColor(BlockState blockState)
    {
        int roomNumberColor = Colors.WHITE;
        if (blockState.getBlock().equals(ModBlocks.RED_ROOM_SIGN)) roomNumberColor = DyeColor.RED.getSignColor();
        if (blockState.getBlock().equals(ModBlocks.ORANGE_ROOM_SIGN)) roomNumberColor = DyeColor.ORANGE.getSignColor();
        if (blockState.getBlock().equals(ModBlocks.YELLOW_ROOM_SIGN)) roomNumberColor = DyeColor.YELLOW.getSignColor();
        if (blockState.getBlock().equals(ModBlocks.GREEN_ROOM_SIGN)) roomNumberColor = DyeColor.GREEN.getSignColor();
        if (blockState.getBlock().equals(ModBlocks.BLUE_ROOM_SIGN)) roomNumberColor = DyeColor.CYAN.getSignColor();
        return roomNumberColor;
    }

    private static void drawRoomNumberFromLeft(BlockState blockState, RoomSignBlockEntity entity,
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

    private static void drawRoomNumberFromRight(BlockState blockState, RoomSignBlockEntity entity,
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
                -textRenderer.getWidth(entity.getRoomNumber()),
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

    private static void drawSubjectLine1(BlockState blockState, RoomSignBlockEntity entity,
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

        textRenderer.draw(entity.getSubjectLine1(),
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

    private static void drawSubjectLine2(BlockState blockState, RoomSignBlockEntity entity,
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

        textRenderer.draw(entity.getSubjectLine2(),
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

    private static void drawTeacherNameLine1(BlockState blockState, RoomSignBlockEntity entity,
                                             MatrixStack matrices,
                                             TextRenderer textRenderer,
                                             VertexConsumerProvider vertexConsumers,
                                             int light,
                                             float x,
                                             float y,
                                             float z,
                                             float yRotation)
    {
        int teacherNameColor = DyeColor.RED.getSignColor();

        matrices.push();

        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(180));
        matrices.translate(x, y, z);
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(yRotation));
        matrices.scale(0.005F, 0.005F, 0.005F);

        textRenderer.draw(entity.getTeacherLine1(),
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

    private static void drawTeacherNameLine2(BlockState blockState, RoomSignBlockEntity entity,
                                             MatrixStack matrices,
                                             TextRenderer textRenderer,
                                             VertexConsumerProvider vertexConsumers,
                                             int light,
                                             float x,
                                             float y,
                                             float z,
                                             float yRotation)
    {
        int teacherNameColor = DyeColor.RED.getSignColor();

        matrices.push();

        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(180));
        matrices.translate(x, y, z);
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(yRotation));
        matrices.scale(0.005F, 0.005F, 0.005F);

        textRenderer.draw(entity.getTeacherLine2(),
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
