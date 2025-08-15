package data.psychologytheory.kcisdeco.entities.renderer;

import data.psychologytheory.kcisdeco.entities.SittableEntity;
import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.state.EntityRenderState;

public class SittableEntityRenderer extends EntityRenderer<SittableEntity, EntityRenderState>
{
    public SittableEntityRenderer(EntityRendererFactory.Context context)
    {
        super(context);
    }

    @Override
    public boolean shouldRender(SittableEntity entity, Frustum frustum, double x, double y, double z)
    {
        return true;
    }

    @Override
    public EntityRenderState createRenderState()
    {
        return new EntityRenderState();
    }
}
