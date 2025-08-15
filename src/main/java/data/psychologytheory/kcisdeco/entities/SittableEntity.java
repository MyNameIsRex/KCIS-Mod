package data.psychologytheory.kcisdeco.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.world.World;

public class SittableEntity extends Entity
{
    public SittableEntity(EntityType<?> type, World world)
    {
        super(type, world);
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {}

    @Override
    public boolean damage(ServerWorld world, DamageSource source, float amount)
    {
        return false;
    }

    @Override
    protected void readCustomData(ReadView view) {}

    @Override
    protected void writeCustomData(WriteView view) {}

    @Override
    protected void removePassenger(Entity passenger)
    {
        super.removePassenger(passenger);
        if (!this.getWorld().isClient)
        {
            this.kill((ServerWorld) this.getWorld());
        }
    }
}
