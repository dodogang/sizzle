package net.dodogang.sizzle.tag;

import net.dodogang.sizzle.Sizzle;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

public class SizzleEntityTypeTags {
    /**
     * A list of entity types that don't collide with ghast jelly.
     */
    public static final Tag<EntityType<?>> GHAST_JELLY_UNCOLLIDABLES = register("ghast_jelly_uncollidables");

    private static Tag<EntityType<?>> register(String path) {
        return TagRegistry.entityType(new Identifier(Sizzle.MOD_ID, path));
    }
}
