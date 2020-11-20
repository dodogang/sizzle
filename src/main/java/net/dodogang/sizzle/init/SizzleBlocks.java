package net.dodogang.sizzle.init;

import net.dodogang.sizzle.Sizzle;
import net.dodogang.sizzle.block.*;
import net.dodogang.sizzle.block.vanilla.*;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SizzleBlocks {
    public static final Block BLAZE_ROD_BLOCK = register("blaze_rod_block",
        new SizzleFacingBlock(
            FabricBlockSettings.of(Material.ORGANIC_PRODUCT, MaterialColor.YELLOW)
                .strength(1, 1)
                .requiresTool()
                .breakByTool(FabricToolTags.PICKAXES)
                .sounds(BlockSoundGroup.WEEPING_VINES_LOW_PITCH)
                .emissiveLighting((state, world, pos) -> true)
                .luminance(state -> 3)
        )
    );
    public static final Block GHAST_JELLY_BLOCK = register(GhastJellyBlock.id,
        new GhastJellyBlock(
            FabricBlockSettings.of(SizzleMaterials.GHAST_JELLY, MaterialColor.WHITE_TERRACOTTA)
                .strength(0.7F, 0.4F)
                .sounds(BlockSoundGroup.HONEY)
                .velocityMultiplier(0.4F)
                .jumpVelocityMultiplier(0.5F)
                .suffocates((state, world, pos) -> false)
                .blockVision((state, world, pos) -> false)
                .solidBlock((state, world, pos) -> false)
                .nonOpaque()
        )
    );

    public static final Block PUMICE = register("pumice",
        new Block(
            FabricBlockSettings.of(Material.STONE, MaterialColor.WHITE_TERRACOTTA)
                .strength(1.5F, 6.0F)
                .requiresTool()
                .breakByTool(FabricToolTags.PICKAXES)
                .sounds(BlockSoundGroup.BASALT)
        )
    );
    public static final Block PUMICE_SLAB = register("pumice_slab", new Block(FabricBlockSettings.copy(PUMICE)));
    public static final Block PUMICE_STAIRS = register("pumice_stairs", new PublicStairsBlock(PUMICE.getDefaultState(), FabricBlockSettings.copy(PUMICE)));
    public static final Block PUMICE_WALL = register("pumice_wall", new WallBlock(FabricBlockSettings.copy(PUMICE)));
    public static final Block POLISHED_PUMICE = register("polished_pumice", new Block(FabricBlockSettings.copy(PUMICE)));
    public static final Block POLISHED_PUMICE_SLAB = register("polished_pumice_slab", new SlabBlock(FabricBlockSettings.copy(POLISHED_PUMICE)));
    public static final Block POLISHED_PUMICE_STAIRS = register("polished_pumice_stairs", new PublicStairsBlock(POLISHED_PUMICE.getDefaultState(), FabricBlockSettings.copy(POLISHED_PUMICE)));
    public static final Block POLISHED_PUMICE_WALL = register("polished_pumice_wall", new WallBlock(FabricBlockSettings.copy(POLISHED_PUMICE)));

    public SizzleBlocks() {}

    public static Block register(String id, Block block, boolean registerItem) {
        Identifier identifier = new Identifier(Sizzle.MOD_ID, id);
        Block registeredBlock = Registry.register(Registry.BLOCK, identifier, block);

        if (registerItem) Registry.register(Registry.ITEM, identifier, new BlockItem(registeredBlock, new Item.Settings().maxCount(64).group(Sizzle.ITEM_GROUP)));

        return registeredBlock;
    }
    public static Block register(String id, Block block) {
        return register(id, block, true);
    }
}
