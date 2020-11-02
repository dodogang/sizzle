package net.dodogang.sizzle.data.tags;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.TagsProvider;
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
