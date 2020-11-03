package net.dodogang.sizzle.data.models;

import net.dodogang.sizzle.common.block.SzBlocks;
import net.dodogang.sizzle.data.models.modelgen.IModelGen;
import net.minecraft.item.Item;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;

import java.util.function.BiConsumer;
import java.util.function.Function;

import static net.dodogang.sizzle.data.models.modelgen.InheritingModelGen.*;

public final class ItemModelTable {
    private static BiConsumer<Item, IModelGen> consumer;

    public static void registerItemModels(BiConsumer<Item, IModelGen> c) {
        consumer = c;

        register(SzBlocks.BLAZE_ROD_BLOCK, item -> inherit(name(item, "block/%s")));
        register(SzBlocks.GHAST_JELLY_BLOCK, item -> inherit(name(item, "block/%s")));
        register(SzBlocks.PUMICE, item -> inherit(name(item, "block/%s")));
        register(SzBlocks.POLISHED_PUMICE, item -> inherit(name(item, "block/%s")));
        register(SzBlocks.PUMICE_SLAB, item -> inherit(name(item, "block/%s")));
        register(SzBlocks.POLISHED_PUMICE_SLAB, item -> inherit(name(item, "block/%s")));
        register(SzBlocks.PUMICE_STAIRS, item -> inherit(name(item, "block/%s")));
        register(SzBlocks.POLISHED_PUMICE_STAIRS, item -> inherit(name(item, "block/%s")));
        register(SzBlocks.PUMICE_WALL, item -> wallInventory(name(item, "block/%s", "_wall")));
        register(SzBlocks.POLISHED_PUMICE_WALL, item -> wallInventory(name(item, "block/%s", "_wall")));
    }



    private static void register(IItemProvider provider, Function<Item, IModelGen> genFactory) {
        Item item = provider.asItem();
        IModelGen gen = genFactory.apply(item);
        consumer.accept(item, gen);
    }

    private static String name(Item item, String nameFormat) {
        ResourceLocation id = item.getRegistryName();
        assert id != null;

        return String.format("%s:%s", id.getNamespace(), String.format(nameFormat, id.getPath()));
    }

    private static String name(Item item) {
        ResourceLocation id = item.getRegistryName();
        assert id != null;
        return id.toString();
    }

    private static String name(Item item, String nameFormat, String omitSuffix) {
        ResourceLocation id = item.getRegistryName();
        assert id != null;

        String path = id.getPath();
        if (path.endsWith(omitSuffix)) {
            path = path.substring(0, path.length() - omitSuffix.length());
        }

        return String.format("%s:%s", id.getNamespace(), String.format(nameFormat, path));
    }

    private ItemModelTable() {
    }
}
