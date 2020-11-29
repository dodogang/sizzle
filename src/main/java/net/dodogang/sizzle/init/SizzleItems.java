package net.dodogang.sizzle.init;

import net.dodogang.sizzle.Sizzle;
import net.dodogang.sizzle.item.WitherBoneMealItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SizzleItems {
    public static final Item WITHER_BONE_MEAL = register("wither_bone_meal", new WitherBoneMealItem(new Item.Settings().group(Sizzle.ITEM_GROUP)));
    public static final Item WITHER_BONE = register("wither_bone");

    public SizzleItems() {}

    public static Item register(String id, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(Sizzle.MOD_ID, id), item);
    }
    public static Item register(String id) {
        return register(id, new Item(new Item.Settings().group(Sizzle.ITEM_GROUP)));
    }
}
