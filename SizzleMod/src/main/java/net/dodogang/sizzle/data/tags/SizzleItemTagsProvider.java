package net.dodogang.sizzle.data.tags;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.TagsProvider;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

import java.nio.file.Path;
import java.util.function.Function;

public class SizzleItemTagsProvider extends TagsProvider<Item> {
    private final Function<ITag.INamedTag<Block>, ITag.Builder> builderGetter;

    @SuppressWarnings("deprecation") // We need Registry.ITEM. Sorry Forge...
    public SizzleItemTagsProvider(DataGenerator gen, SizzleBlockTagsProvider blockTags) {
        super(gen, Registry.ITEM);
        this.builderGetter = blockTags::getBuilder;
    }

    @Override
    protected void registerTags() {
        copy(BlockTags.SLABS, ItemTags.SLABS);
        copy(BlockTags.STAIRS, ItemTags.STAIRS);
        copy(BlockTags.WALLS, ItemTags.WALLS);
    }


    protected ITag.Builder getBuilder(ITag.INamedTag<Item> namedTag) {
        return tagToBuilder.computeIfAbsent(namedTag.getId(), id -> new ITag.Builder());
    }

    protected void copy(ITag.INamedTag<Block> blockTag, ITag.INamedTag<Item> itemTag) {
        ITag.Builder itemBuilder = getBuilder(itemTag);
        ITag.Builder blockBuilder = builderGetter.apply(blockTag);
        blockBuilder.streamEntries().forEach(itemBuilder::add);
    }

    @Override
    protected Path makePath(ResourceLocation id) {
        return generator.getOutputFolder().resolve("data/" + id.getNamespace() + "/tags/items/" + id.getPath() + ".json");
    }

    @Override
    public String getName() {
        return "Sizzle/ItemTags";
    }
}
