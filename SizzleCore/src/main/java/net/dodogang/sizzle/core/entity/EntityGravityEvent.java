package net.dodogang.sizzle.core.entity;

import net.minecraft.entity.Entity;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;

@Cancelable
public class EntityGravityEvent extends Event {
    private final Entity entity;
    private boolean gravity;

    public EntityGravityEvent(Entity entity) {
        this.entity = entity;
    }

    public Entity getEntity() {
        return entity;
    }

    public void disableGravity() {
        gravity = false;
    }

    public void enableGravity() {
        gravity = true;
    }

    public boolean getGravityEnabled() {
        return gravity;
    }
}
