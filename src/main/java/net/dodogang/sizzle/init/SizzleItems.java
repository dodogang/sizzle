package net.dodogang.sizzle.init;

import net.dodogang.sizzle.Sizzle;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SizzleItems {
    public SizzleItems() {}

    public static Item register(String id, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(Sizzle.MOD_ID, id), item);
    }
}
