package net.dodogang.sizzle.init;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricMaterialBuilder;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;

public class SizzleMaterials {
    public static final Material GHAST_JELLY = new FabricMaterialBuilder(MaterialColor.WHITE_TERRACOTTA)
        .allowsMovement()
        .lightPassesThrough()
        .notSolid()
        .build();
}
