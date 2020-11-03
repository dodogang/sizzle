package net.dodogang.sizzle.common.handler;

import net.dodogang.sizzle.common.block.SzMaterials;
import net.dodogang.sizzle.core.entity.EntityGravityEvent;
import net.dodogang.sizzle.core.entity.EntityInMaterialEvent;
import net.dodogang.sizzle.core.entity.IGhastJellyInteractive;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EntityInGhastJellyHandler {
    @SubscribeEvent
    public void onEntityInMaterial(EntityInMaterialEvent event) {
        Entity self = event.getEntity();
        IGhastJellyInteractive interactive = (IGhastJellyInteractive) self;
        interactive.setNotInGhastJelly();

        if (event.isInMaterial(SzMaterials.GHAST_JELLY)) {
            Vector3d motion = self.getMotion();
            double destVelo = self.isSneaking() ? -0.06 : 0.03;
            double submergedHeight = event.getSubmergingHeight(SzMaterials.GHAST_JELLY);

            if (submergedHeight > 1e-7 + 1 / 16d) {
                interactive.setInGhastJelly();
                double distToMaxYMotion = destVelo - motion.y;
                self.setMotion(motion.x, motion.y + distToMaxYMotion * 0.7, motion.z);
            } else {
                self.setMotion(motion.x * 0.8, motion.y, motion.z * 0.8);
            }
            self.fallDistance = 0;
        }
    }

    @SubscribeEvent
    public void onEntityGravity(EntityGravityEvent event) {
        Entity self = event.getEntity();
        IGhastJellyInteractive interactive = (IGhastJellyInteractive) self;
        if (interactive.isInGhastJelly()) {
            event.disableGravity();
            event.setCanceled(true);
        }
    }
}
