package net.dodogang.sizzle.common.item;

import net.dodogang.sizzle.common.Sizzle;
import net.dodogang.sizzle.util.IRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ObjectHolder;

import javax.annotation.Nonnull;

@ObjectHolder("sizzle")
public abstract class SzItems {


    public static void registerItems(IRegistry<Item> registry) {

    }

    @OnlyIn(Dist.CLIENT)
    public static void setupClient() {

    }

    private SzItems() {
    }

    private static <I extends Item> I item(String id, I item) {
        item.setRegistryName(Sizzle.resLoc(id));
        return item;
    }

    private static Item.Properties inGroup(ItemGroup group) {
        return new Item.Properties().group(group);
    }


    @Nonnull
    @SuppressWarnings("ConstantConditions")
    private static Item inj() {
        return null;
    }
}
