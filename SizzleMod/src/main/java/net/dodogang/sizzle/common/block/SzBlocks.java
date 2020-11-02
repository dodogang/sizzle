package net.dodogang.sizzle.common.block;

import net.dodogang.sizzle.common.Sizzle;
import net.dodogang.sizzle.util.IRegistry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ObjectHolder;

import javax.annotation.Nonnull;

@ObjectHolder("sizzle")
public abstract class SzBlocks {
    public static final Block BASALTALLSIDES = inj();

    public static void registerBlocks(IRegistry<Block> registry) {
        registry.registerAll(
            block("basaltallsides", new Block(AbstractBlock.Properties.create(Material.ROCK).sound(SoundType.BASALT)))
        );
    }

    public static void registerItems(IRegistry<Item> registry) {
        registry.registerAll(
            item(BASALTALLSIDES, ItemGroup.BUILDING_BLOCKS)
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
