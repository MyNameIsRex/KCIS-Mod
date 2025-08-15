package data.psychologytheory.kcisdeco.entities;

import data.psychologytheory.kcisdeco.KCISDecorationMod;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModEntities
{
    private static final RegistryKey<EntityType<?>> SITTABLE_ENTITY_KEY =
            RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(KCISDecorationMod.MOD_ID, "sittable_entity"));

    public static final EntityType<SittableEntity> SITTABLE_ENTITY = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(KCISDecorationMod.MOD_ID, "sittable_entity"),
            EntityType.Builder.create(SittableEntity::new, SpawnGroup.MISC).dimensions(.5F, .5F).build(SITTABLE_ENTITY_KEY));

    public static void registerEntities() {}
}
