package net.dodogang.sizzle.init;

import net.dodogang.sizzle.Sizzle;
import net.dodogang.sizzle.block.*;
import net.dodogang.sizzle.block.vanilla.*;
import net.dodogang.sizzle.state.property.SizzleProperties;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.BedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SizzleBlocks {

    //
    // CAP BLOCKS
    //

    public static final Block BROWN_CAP = register("brown_cap", new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_WOOD)));
    public static final Block BROWN_CAP_PLANKS = register("brown_cap_planks", new Block(FabricBlockSettings.copy(Blocks.OAK_PLANKS)));
    public static final Block BROWN_CAP_SLAB = register("brown_cap_slab", new SlabBlock(FabricBlockSettings.copy(Blocks.OAK_SLAB)));
    public static final Block BROWN_CAP_STAIRS = register("brown_cap_stairs", new PublicStairsBlock(BROWN_CAP_PLANKS.getDefaultState(), FabricBlockSettings.copy(Blocks.OAK_STAIRS)));
    public static final Block BROWN_CAP_BEAM = registerBeam("brown_cap");
    public static final Block BROWN_CAP_BED = register("brown_cap_bed", new CapBedBlock(FabricBlockSettings.copy(Blocks.BROWN_BED)));

    public static final Block RED_CAP = register("red_cap", new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_WOOD)));
    public static final Block RED_CAP_PLANKS = register("red_cap_planks", new Block(FabricBlockSettings.copy(Blocks.OAK_PLANKS)));
    public static final Block RED_CAP_SLAB = register("red_cap_slab", new SlabBlock(FabricBlockSettings.copy(Blocks.OAK_SLAB)));
    public static final Block RED_CAP_STAIRS = register("red_cap_stairs", new PublicStairsBlock(RED_CAP_PLANKS.getDefaultState(), FabricBlockSettings.copy(Blocks.OAK_STAIRS)));
    public static final Block RED_CAP_BEAM = registerBeam("red_cap");
    public static final Block RED_CAP_BED = register("red_cap_bed", new CapBedBlock(FabricBlockSettings.copy(Blocks.RED_BED)));

    public static final Block RAND_CAP = register("rand_cap", new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_WOOD)));
    public static final Block RAND_CAP_PLANKS = register("rand_cap_planks", new Block(FabricBlockSettings.copy(Blocks.OAK_PLANKS)));
    public static final Block RAND_CAP_SLAB = register("rand_cap_slab", new SlabBlock(FabricBlockSettings.copy(Blocks.OAK_SLAB)));
    public static final Block RAND_CAP_STAIRS = register("rand_cap_stairs", new PublicStairsBlock(RAND_CAP_PLANKS.getDefaultState(), FabricBlockSettings.copy(Blocks.OAK_STAIRS)));
    public static final Block RAND_CAP_BEAM = registerBeam("rand_cap");
    public static final Block RAND_CAP_BED = register("rand_cap_bed", new CapBedBlock(true, FabricBlockSettings.copy(Blocks.ORANGE_BED)));

    //
    // PUMICE
    //

    public static final Block PUMICE = register("pumice",
        new Block(
            FabricBlockSettings.of(Material.STONE, MaterialColor.WHITE_TERRACOTTA)
                .strength(1.5F, 6.0F)
                .requiresTool()
                .breakByTool(FabricToolTags.PICKAXES)
                .sounds(BlockSoundGroup.BASALT)
        )
    );
    public static final Block PUMICE_SLAB = register("pumice_slab", new SlabBlock(FabricBlockSettings.copy(PUMICE)));
    public static final Block PUMICE_STAIRS = register("pumice_stairs", new PublicStairsBlock(PUMICE.getDefaultState(), FabricBlockSettings.copy(PUMICE)));
    public static final Block PUMICE_WALL = register("pumice_wall", new WallBlock(FabricBlockSettings.copy(PUMICE)));
    public static final Block POLISHED_PUMICE = register("polished_pumice", new Block(FabricBlockSettings.copy(PUMICE)));
    public static final Block POLISHED_PUMICE_SLAB = register("polished_pumice_slab", new SlabBlock(FabricBlockSettings.copy(POLISHED_PUMICE)));
    public static final Block POLISHED_PUMICE_STAIRS = register("polished_pumice_stairs", new PublicStairsBlock(POLISHED_PUMICE.getDefaultState(), FabricBlockSettings.copy(POLISHED_PUMICE)));
    public static final Block POLISHED_PUMICE_WALL = register("polished_pumice_wall", new WallBlock(FabricBlockSettings.copy(POLISHED_PUMICE)));

    //
    // SOULSTONE
    //

    public static final Block SOULSTONE = register("soulstone",
        new Block(
            FabricBlockSettings.of(Material.STONE, MaterialColor.GRAY)
                .strength(1.5F, 6.0F)
                .requiresTool()
                .breakByTool(FabricToolTags.PICKAXES)
                .sounds(BlockSoundGroup.BASALT)
        )
    );
    public static final Block SOULSTONE_SLAB = register("soulstone_slab", new SlabBlock(FabricBlockSettings.copy(SOULSTONE)));
    public static final Block SOULSTONE_STAIRS = register("soulstone_stairs", new PublicStairsBlock(SOULSTONE.getDefaultState(), FabricBlockSettings.copy(SOULSTONE)));
    public static final Block SOULSTONE_WALL = register("soulstone_wall", new WallBlock(FabricBlockSettings.copy(SOULSTONE)));
    public static final Block SOULSTONE_BRICKS = register("soulstone_bricks", new Block(FabricBlockSettings.copy(SOULSTONE)));
    public static final Block SOULSTONE_BRICK_SLAB = register("soulstone_brick_slab", new SlabBlock(FabricBlockSettings.copy(SOULSTONE_BRICKS)));
    public static final Block SOULSTONE_BRICK_STAIRS = register("soulstone_brick_stairs", new PublicStairsBlock(SOULSTONE_BRICKS.getDefaultState(), FabricBlockSettings.copy(SOULSTONE_BRICKS)));
    public static final Block SOULSTONE_BRICK_WALL = register("soulstone_brick_wall", new WallBlock(FabricBlockSettings.copy(SOULSTONE_BRICKS)));
    public static final Block SMOOTH_SOULSTONE = register("smooth_soulstone", new Block(FabricBlockSettings.copy(SOULSTONE)));
    public static final Block CARVED_SOULSTONE = register("carved_soulstone", new Block(FabricBlockSettings.copy(SOULSTONE)));
    public static final Block CHISELED_SOULSTONE = register("chiseled_soulstone", new Block(FabricBlockSettings.copy(SOULSTONE)));

    //
    // ROOTS
    //

    public static final Block TALL_CRIMSON_ROOTS = register("tall_crimson_roots", new TallRootsBlock(FabricBlockSettings.copy(Blocks.CRIMSON_ROOTS)));
    public static final Block TALL_WARPED_ROOTS = register("tall_warped_roots", new TallRootsBlock(FabricBlockSettings.copy(Blocks.WARPED_ROOTS)));
    public static final Block TALLER_CRIMSON_ROOTS = register("taller_crimson_roots", new TallerRootsBlock(FabricBlockSettings.copy(Blocks.CRIMSON_ROOTS)));
    public static final Block TALLER_WARPED_ROOTS = register("taller_warped_roots", new TallerRootsBlock(FabricBlockSettings.copy(Blocks.WARPED_ROOTS)));

    //
    // STANDALONE BLOCKS
    //

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

    public static final Block MAGMA_TONGUE = register(MagmaTongueBlock.id,
        new MagmaTongueBlock(
            FabricBlockSettings.of(Material.NETHER_SHOOTS, MaterialColor.ORANGE_TERRACOTTA)
                .luminance(
                    (state) -> {
                        return state.get(Properties.WATERLOGGED)
                            ? 0
                            : 3 + (2 * state.get(SizzleProperties.TONGUES));
                    }
                )
                .sounds(BlockSoundGroup.NETHER_SPROUTS)
                .nonOpaque()
        )
    );
    public static final Block WITHER_BONE_BLOCK = register("wither_bone_block",
        new PillarBlock(
            FabricBlockSettings.of(Material.STONE, MaterialColor.BLACK)
                .requiresTool()
                .strength(2.0F)
                .sounds(BlockSoundGroup.BONE)
        )
    );

    public SizzleBlocks() {}

    public static Block register(String id, Block block, boolean registerItem) {
        Identifier identifier = new Identifier(Sizzle.MOD_ID, id);
        Block registeredBlock = Registry.register(Registry.BLOCK, identifier, block);

        if (registerItem) {
            int maxCount = block instanceof BedBlock ? 1 : 64;
            Registry.register(Registry.ITEM, identifier, new BlockItem(registeredBlock, new Item.Settings().maxCount(maxCount).group(Sizzle.ITEM_GROUP)));
        }

        return registeredBlock;
    }
    public static Block register(String id, Block block) {
        return register(id, block, true);
    }

    public static Block registerBeam(String id) {
        return register(
            id + "_beam",
            new BeamBlock(
                FabricBlockSettings.of(Material.WOOD)
                    .strength(2.0F, 2.0F)
                    .nonOpaque()
                    .sounds(BlockSoundGroup.WOOD)
            )
        );
    }
}
