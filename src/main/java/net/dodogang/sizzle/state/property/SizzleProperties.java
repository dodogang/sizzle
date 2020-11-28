package net.dodogang.sizzle.state.property;

import net.dodogang.sizzle.block.enums.TallerBlockPart;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.IntProperty;

public class SizzleProperties {
    public static final IntProperty TONGUES = IntProperty.of("tongues", 1, 5);
    public static final EnumProperty<TallerBlockPart> TALLER_BLOCK_PART = EnumProperty.of("part", TallerBlockPart.class);
}
