package net.dodogang.sizzle.tag;

import net.dodogang.sizzle.Sizzle;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.block.Block;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

public class SizzleBlockTags {
    public static final Tag<Block> WITHER_BONE_MEAL_DEFERTILIZABLE = register("wither_bone_meal_defertilizable");

    private static Tag<Block> register(String path) {
        return TagRegistry.block(new Identifier(Sizzle.MOD_ID, path));
    }
}
