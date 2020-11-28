package net.dodogang.sizzle.init;

import com.mojang.datafixers.types.Type;

import net.dodogang.sizzle.Sizzle;
import net.dodogang.sizzle.block.entity.CapBedBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.datafixer.TypeReferences;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.registry.Registry;

public class SizzleBlockEntities {
    public static final BlockEntityType<CapBedBlockEntity> CAP_BED = register("cap_bed", BlockEntityType.Builder.create(CapBedBlockEntity::new, SizzleBlocks.BROWN_CAP_BED, SizzleBlocks.RED_CAP_BED, SizzleBlocks.RAND_CAP_BED));

    private static <T extends BlockEntity> BlockEntityType<T> register(String id, BlockEntityType.Builder<T> builder) {
        Type<?> type = Util.getChoiceType(TypeReferences.BLOCK_ENTITY, id);
        return Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(Sizzle.MOD_ID, id), builder.build(type));
    }
}
