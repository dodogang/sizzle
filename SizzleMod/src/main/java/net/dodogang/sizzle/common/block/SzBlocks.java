package net.dodogang.sizzle.common.block;

import net.dodogang.sizzle.common.Sizzle;
import net.dodogang.sizzle.util.IRegistry;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.registries.ObjectHolder;

import javax.annotation.Nonnull;

@ObjectHolder("sizzle")
public abstract class SzBlocks {
    public static final Block BLAZE_ROD_BLOCK = inj();
    public static final Block GHAST_JELLY_BLOCK = inj();

    public static final Block PUMICE = inj();
    public static final Block POLISHED_PUMICE = inj();
    public static final Block PUMICE_SLAB = inj();
    public static final Block POLISHED_PUMICE_SLAB = inj();
    public static final Block PUMICE_STAIRS = inj();
    public static final Block POLISHED_PUMICE_STAIRS = inj();
    public static final Block PUMICE_WALL = inj();
    public static final Block POLISHED_PUMICE_WALL = inj();

    public static void registerBlocks(IRegistry<Block> registry) {
        registry.registerAll(
            blazeRodBlock("blaze_rod_block"),
            ghastJellyBlock("ghast_jelly_block"),

            pumice("pumice"),
            pumice("polished_pumice"),
            pumiceSlab("pumice_slab"),
            pumiceSlab("polished_pumice_slab"),
            pumiceStairs("pumice_stairs"),
            pumiceStairs("polished_pumice_stairs"),
            pumiceWall("pumice_wall"),
            pumiceWall("polished_pumice_wall")
        );
    }

    public static void registerItems(IRegistry<Item> registry) {
        registry.registerAll(
            item(BLAZE_ROD_BLOCK, ItemGroup.BUILDING_BLOCKS),
            item(GHAST_JELLY_BLOCK, ItemGroup.DECORATIONS),

            item(PUMICE, ItemGroup.BUILDING_BLOCKS),
            item(POLISHED_PUMICE, ItemGroup.BUILDING_BLOCKS),
            item(PUMICE_SLAB, ItemGroup.BUILDING_BLOCKS),
            item(POLISHED_PUMICE_SLAB, ItemGroup.BUILDING_BLOCKS),
            item(PUMICE_STAIRS, ItemGroup.BUILDING_BLOCKS),
            item(POLISHED_PUMICE_STAIRS, ItemGroup.BUILDING_BLOCKS),
            item(PUMICE_WALL, ItemGroup.DECORATIONS),
            item(POLISHED_PUMICE_WALL, ItemGroup.DECORATIONS)
        );
    }

    public static void setup() {
    }

    @OnlyIn(Dist.CLIENT)
    public static void setupClient() {
        BlockColors blockColors = Minecraft.getInstance().getBlockColors();
        ItemColors itemColors = Minecraft.getInstance().getItemColors();
    }

    private SzBlocks() {
    }

    private static Block block(String id, Block block) {
        return block.setRegistryName(Sizzle.resLoc(id));
    }

    private static Block pumice(String id) {
        return block(id, new Block(
            AbstractBlock.Properties.create(Material.ROCK, MaterialColor.WHITE_TERRACOTTA)
                                    .hardnessAndResistance(1.5f, 6)
                                    .requiresTool()
                                    .harvestTool(ToolType.PICKAXE)
                                    .sound(SoundType.BASALT)
        ));
    }

    private static Block pumiceStairs(String id) {
        return block(id, new SimpleStairsBlock(
            AbstractBlock.Properties.create(Material.ROCK, MaterialColor.WHITE_TERRACOTTA)
                                    .hardnessAndResistance(1.5f, 6)
                                    .requiresTool()
                                    .harvestTool(ToolType.PICKAXE)
                                    .sound(SoundType.BASALT)
        ));
    }

    private static Block pumiceSlab(String id) {
        return block(id, new SlabBlock(
            AbstractBlock.Properties.create(Material.ROCK, MaterialColor.WHITE_TERRACOTTA)
                                    .hardnessAndResistance(1.5f, 6)
                                    .requiresTool()
                                    .harvestTool(ToolType.PICKAXE)
                                    .sound(SoundType.BASALT)
        ));
    }

    private static Block pumiceWall(String id) {
        return block(id, new WallBlock(
            AbstractBlock.Properties.create(Material.ROCK, MaterialColor.WHITE_TERRACOTTA)
                                    .hardnessAndResistance(1.5f, 6)
                                    .requiresTool()
                                    .harvestTool(ToolType.PICKAXE)
                                    .sound(SoundType.BASALT)
        ));
    }

    private static Block blazeRodBlock(String id) {
        return block(id, new FacingBlock(
            AbstractBlock.Properties.create(Material.ORGANIC, MaterialColor.YELLOW)
                                    .hardnessAndResistance(1)
                                    .requiresTool()
                                    .harvestTool(ToolType.PICKAXE)
                                    .sound(SoundType.WEEPING_VINES_LOW_PITCH)
                                    .emissiveLighting((state, world, pos) -> true)
                                    .luminance(state -> 3)
        ));
    }

    private static Block ghastJellyBlock(String id) {
        return block(id, new GhastJellyBlock(
            AbstractBlock.Properties.create(SzMaterials.GHAST_JELLY, MaterialColor.WHITE_TERRACOTTA)
                                    .hardnessAndResistance(0.7f, 0.4f)
                                    .sound(SoundType.HONEY)
                                    .velocityMultiplier(0.4f)
                                    .jumpVelocityMultiplier(0.5f)
                                    .suffocates((state, world, pos) -> false)
                                    .blockVision((state, world, pos) -> false)
                                    .solidBlock((state, world, pos) -> false)
                                    .nonOpaque()
        ));
    }

    private static BlockItem item(Block block, Item.Properties props) {
        ResourceLocation id = block.getRegistryName();
        assert id != null;
        BlockItem item = new BlockItem(block, props);
        item.setRegistryName(id);
        return item;
    }

    private static BlockItem item(Block block, ItemGroup group) {
        return item(block, new Item.Properties().group(group));
    }

    @Nonnull
    @SuppressWarnings("ConstantConditions")
    private static Block inj() {
        return null;
    }
}
