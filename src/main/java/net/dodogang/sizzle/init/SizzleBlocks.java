package net.dodogang.sizzle.init;

import me.andante.chord.block.BeamBlock;
import me.andante.chord.block.vanilla.PublicStairsBlock;
import net.dodogang.sizzle.Sizzle;
import net.dodogang.sizzle.block.*;
import net.dodogang.sizzle.state.property.SizzleProperties;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

@SuppressWarnings("unused")
public class SizzleBlocks {
    /*
        CAP BLOCKS
    */

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

    /*
        PUMICE
    */

    public static final Block PUMICE = register("pumice",
        new Block(
            FabricBlockSettings.of(Material.STONE, MaterialColor.WHITE_TERRACOTTA)
                .strength(1.5F, 6.0F)
                .requiresTool().breakByTool(FabricToolTags.PICKAXES)
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

    /*
        SOUL SANDSTONE
    */

    public static final Block SOUL_SANDSTONE = register("soul_sandstone",
        new Block(
            FabricBlockSettings.of(Material.STONE, MaterialColor.GRAY)
                .strength(1.5F, 6.0F)
                .requiresTool().breakByTool(FabricToolTags.PICKAXES)
                .sounds(BlockSoundGroup.BASALT)
        )
    );
    public static final Block CHISELED_SOUL_SANDSTONE = copy("chiseled_soul_sandstone", SOUL_SANDSTONE);
    public static final Block CUT_SOUL_SANDSTONE = copy("cut_soul_sandstone", SOUL_SANDSTONE);
    public static final Block SMOOTH_SOUL_SANDSTONE = copy("smooth_soul_sandstone", SOUL_SANDSTONE);
    public static final Block SOUL_SANDSTONE_SLAB = createSlab(SOUL_SANDSTONE);
    public static final Block CUT_SOUL_SANDSTONE_SLAB = createSlab(CUT_SOUL_SANDSTONE);
    public static final Block SMOOTH_SOUL_SANDSTONE_SLAB = createSlab(SMOOTH_SOUL_SANDSTONE);
    public static final Block SOUL_SANDSTONE_STAIRS = createStairs(SOUL_SANDSTONE);
    public static final Block CUT_SOUL_SANDSTONE_STAIRS = createStairs(CUT_SOUL_SANDSTONE);
    public static final Block SMOOTH_SOUL_SANDSTONE_STAIRS = createStairs(SMOOTH_SOUL_SANDSTONE);
    public static final Block SOUL_SANDSTONE_WALL = createWall(SOUL_SANDSTONE);
    public static final Block CUT_SOUL_SANDSTONE_WALL = createWall(CUT_SOUL_SANDSTONE);
    public static final Block SMOOTH_SOUL_SANDSTONE_WALL = createWall(SMOOTH_SOUL_SANDSTONE);

    public static final Block GLAZED_SOUL_SAND = register("glazed_soul_sand", new SandBlock(10329495, FabricBlockSettings.copy(Blocks.SAND)));
    public static final Block GLAZED_SOUL_SANDSTONE = register("glazed_soul_sandstone", new Block(FabricBlockSettings.copy(SOUL_SANDSTONE)));
    public static final Block CHISELED_GLAZED_SOUL_SANDSTONE = copy("chiseled_glazed_soul_sandstone", GLAZED_SOUL_SANDSTONE);
    public static final Block CUT_GLAZED_SOUL_SANDSTONE = copy("cut_glazed_soul_sandstone", GLAZED_SOUL_SANDSTONE);
    public static final Block SMOOTH_GLAZED_SOUL_SANDSTONE = copy("smooth_glazed_soul_sandstone", GLAZED_SOUL_SANDSTONE);
    public static final Block GLAZED_SOUL_SANDSTONE_SLAB = createSlab(GLAZED_SOUL_SANDSTONE);
    public static final Block CUT_GLAZED_SOUL_SANDSTONE_SLAB = createSlab(CUT_GLAZED_SOUL_SANDSTONE);
    public static final Block SMOOTH_GLAZED_SOUL_SANDSTONE_SLAB = createSlab(SMOOTH_GLAZED_SOUL_SANDSTONE);
    public static final Block GLAZED_SOUL_SANDSTONE_STAIRS = createStairs(GLAZED_SOUL_SANDSTONE);
    public static final Block CUT_GLAZED_SOUL_SANDSTONE_STAIRS = createStairs(CUT_GLAZED_SOUL_SANDSTONE);
    public static final Block SMOOTH_GLAZED_SOUL_SANDSTONE_STAIRS = createStairs(SMOOTH_GLAZED_SOUL_SANDSTONE);
    public static final Block GLAZED_SOUL_SANDSTONE_WALL = createWall(GLAZED_SOUL_SANDSTONE);
    public static final Block CUT_GLAZED_SOUL_SANDSTONE_WALL = createWall(CUT_GLAZED_SOUL_SANDSTONE);
    public static final Block SMOOTH_GLAZED_SOUL_SANDSTONE_WALL = createWall(SMOOTH_GLAZED_SOUL_SANDSTONE);

    /*
        ROOTS
    */

    public static final Block TALL_CRIMSON_ROOTS = register("tall_crimson_roots", new TallRootsBlock(FabricBlockSettings.copy(Blocks.CRIMSON_ROOTS)));
    public static final Block TALL_WARPED_ROOTS = register("tall_warped_roots", new TallRootsBlock(FabricBlockSettings.copy(Blocks.WARPED_ROOTS)));
    public static final Block TALLER_CRIMSON_ROOTS = register("taller_crimson_roots", new TallerRootsBlock(FabricBlockSettings.copy(Blocks.CRIMSON_ROOTS)));
    public static final Block TALLER_WARPED_ROOTS = register("taller_warped_roots", new TallerRootsBlock(FabricBlockSettings.copy(Blocks.WARPED_ROOTS)));

    /*
        STANDALONE BLOCKS
    */

    public static final Block BLAZE_ROD_BLOCK = register("blaze_rod_block",
        new SizzleFacingBlock(
            FabricBlockSettings.of(Material.ORGANIC_PRODUCT, MaterialColor.YELLOW)
                .strength(1, 1)
                .requiresTool().breakByTool(FabricToolTags.PICKAXES)
                .sounds(BlockSoundGroup.WEEPING_VINES_LOW_PITCH).luminance(state -> 6)
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
                .luminance(state -> state.get(Properties.WATERLOGGED) ? 0 : 3 + (2 * state.get(SizzleProperties.TONGUES))
                ).sounds(BlockSoundGroup.NETHER_SPROUTS)
                .nonOpaque().noCollision()
        )
    );
    public static final Block WITHER_BONE_BLOCK = register("wither_bone_block",
        new PillarBlock(
            FabricBlockSettings.of(Material.STONE, MaterialColor.BLACK)
                .requiresTool().strength(2.0F)
                .sounds(BlockSoundGroup.BONE)
        )
    );
    public static final Block BASALT_BRICKS = register("basalt_bricks", new PillarBlock(FabricBlockSettings.copy(Blocks.BASALT)));

    public SizzleBlocks() {}

    private static Block register(String id, Block block, boolean registerItem) {
        Identifier identifier = new Identifier(Sizzle.MOD_ID, id);
        Block registeredBlock = Registry.register(Registry.BLOCK, identifier, block);

        if (registerItem) {
            Registry.register(Registry.ITEM, identifier, new BlockItem(registeredBlock, new Item.Settings().maxCount(block instanceof BedBlock ? 1 : 64).group(Sizzle.ITEM_GROUP)));
        }

        return registeredBlock;
    }
    private static Block register(String id, Block block) {
        return register(id, block, true);
    }

    private static Block createSlab(Block block) {
        return createSlab(getBlockId(block), block);
    }
    private static Block createSlab(String id, Block block) {
        return register(id + "_slab", new SlabBlock(FabricBlockSettings.copy(block)));
    }
    private static Block createWall(Block block) {
        return createWall(getBlockId(block), block);
    }
    private static Block createWall(String id, Block block) {
        return register(id + "_wall", new WallBlock(FabricBlockSettings.copy(block)));
    }
    private static Block createStairs(Block block) {
        return createStairs(getBlockId(block), block);
    }
    private static Block createStairs(String id, Block block) {
        return register(id + "_stairs", new PublicStairsBlock(block.getDefaultState(), FabricBlockSettings.copy(block)));
    }

    private static Block copy(String id, Block block) {
        return register(id, new Block(FabricBlockSettings.copy(block)));
    }

    private static Block registerBeam(String id) {
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

    private static String getBlockId(Block block) {
        return Registry.BLOCK.getId(block).getPath();
    }
}
