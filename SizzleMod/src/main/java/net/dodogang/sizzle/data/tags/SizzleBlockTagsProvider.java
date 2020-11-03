package net.dodogang.sizzle.data.tags;

import net.dodogang.sizzle.common.block.SzBlocks;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.TagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

import java.nio.file.Path;

public class SizzleBlockTagsProvider extends TagsProvider<Block> {
    @SuppressWarnings("deprecation") // We need Registry.BLOCK. Sorry Forge...
    public SizzleBlockTagsProvider(DataGenerator gen) {
        super(gen, Registry.BLOCK);
    }

    @Override
    protected void registerTags() {
        getOrCreateTagBuilder(BlockTags.SLABS)
            .add(SzBlocks.PUMICE_SLAB)
            .add(SzBlocks.POLISHED_PUMICE_SLAB);

        getOrCreateTagBuilder(BlockTags.STAIRS)
            .add(SzBlocks.PUMICE_STAIRS)
            .add(SzBlocks.POLISHED_PUMICE_STAIRS);

        getOrCreateTagBuilder(BlockTags.WALLS)
            .add(SzBlocks.PUMICE_WALL)
            .add(SzBlocks.POLISHED_PUMICE_WALL);

    }

    protected ITag.Builder getBuilder(ITag.INamedTag<Block> namedTag) {
        return tagToBuilder.computeIfAbsent(namedTag.getId(), id -> new ITag.Builder());
    }

    @Override
    protected Path makePath(ResourceLocation id) {
        return generator.getOutputFolder().resolve("data/" + id.getNamespace() + "/tags/blocks/" + id.getPath() + ".json");
    }

    @Override
    public String getName() {
        return "Sizzle/BlockTags";
    }
}
