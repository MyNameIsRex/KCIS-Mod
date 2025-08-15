package data.psychologytheory.kcisdeco.screens;

import data.psychologytheory.kcisdeco.networking.packets.payload.RoomSignPayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

public class RoomSignScreen extends HandledScreen<RoomSignScreenHandler>
{
    private static final Identifier TEXTURE = Identifier.ofVanilla("textures/gui/container/anvil.png");
    private static final Identifier TEXT_FIELD_TEXTURE = Identifier.ofVanilla("container/anvil/text_field");

    private TextFieldWidget roomSignContents;

    public RoomSignScreen(RoomSignScreenHandler handler, PlayerInventory inventory, Text title)
    {
        super(handler, inventory, title);
    }

    @Override
    protected void init()
    {
        super.init();

        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;

        this.roomSignContents = new TextFieldWidget(this.textRenderer, x + 62, y + 24, 103,12, Text.literal("Room Sign"));
        this.roomSignContents.setDrawsBackground(false);
        this.roomSignContents.setFocusUnlocked(false);
        this.roomSignContents.setMaxLength(50);
        this.roomSignContents.setChangedListener(this::handleChanges);
        this.roomSignContents.setText(this.getScreenHandler().getBlockEntity().getRoomNumber() + "|" + this.getScreenHandler().getBlockEntity().getSubject() + "|" + this.getScreenHandler().getBlockEntity().getTeacher());

        this.addDrawableChild(this.roomSignContents);
    }

    private void handleChanges(String string)
    {
        String[] contents = string.split("\\|");
        if (contents.length == 0) return;
        this.getScreenHandler().getBlockEntity().setRoomNumber(contents[0]);

        if (contents.length == 1) return;
        this.getScreenHandler().getBlockEntity().setSubject(contents[1]);

        if (contents.length == 2) return;
        this.getScreenHandler().getBlockEntity().setTeacher(contents[2]);

        ClientPlayNetworking.send(new RoomSignPayload(string, this.getScreenHandler().getBlockEntity().getPos()));
    }

    @Override
    protected void drawBackground(DrawContext context, float deltaTicks, int mouseX, int mouseY)
    {
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;

        context.drawTexture(RenderPipelines.GUI_TEXTURED, TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight, 256, 256);
        context.drawGuiTexture(RenderPipelines.GUI_TEXTURED, TEXT_FIELD_TEXTURE, this.x + 59, this.y + 20, 110, 16);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers)
    {
        if (keyCode == GLFW.GLFW_KEY_ESCAPE)
        {
            this.client.player.closeHandledScreen();
        }

        return this.roomSignContents.keyPressed(keyCode, scanCode, modifiers) || this.roomSignContents.isActive() || super.keyPressed(keyCode, scanCode, modifiers);
    }
}
