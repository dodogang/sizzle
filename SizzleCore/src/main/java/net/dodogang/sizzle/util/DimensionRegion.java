package net.dodogang.sizzle.util;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

/**
 * Represents a region in a specific dimension.
 */
public class DimensionRegion {
    private final AxisAlignedBB box;
    private final RegistryKey<World> dimen;

    public DimensionRegion(AxisAlignedBB box, RegistryKey<World> dimen) {
        this.box = box;
        this.dimen = dimen;
    }

    public AxisAlignedBB getRegion() {
        return box;
    }

    public RegistryKey<World> getDimension() {
        return dimen;
    }
}
