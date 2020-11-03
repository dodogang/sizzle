package net.dodogang.sizzle.core.entity;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraftforge.eventbus.api.Event;

import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;

public class EntityInMaterialEvent extends Event {
    private final Entity entity;
    private final Predicate<Material> isInMaterial;
    private final ToDoubleFunction<Material> materialSubmergedHeight;

    public EntityInMaterialEvent(Entity entity, Predicate<Material> isInMaterial, ToDoubleFunction<Material> materialSubmergedHeight) {
        this.entity = entity;
        this.isInMaterial = isInMaterial;
        this.materialSubmergedHeight = materialSubmergedHeight;
    }

    public boolean isInMaterial(Material material) {
        return isInMaterial.test(material);
    }

    public double getSubmergingHeight(Material material) {
        return materialSubmergedHeight.applyAsDouble(material);
    }

    public Entity getEntity() {
        return entity;
    }
}
