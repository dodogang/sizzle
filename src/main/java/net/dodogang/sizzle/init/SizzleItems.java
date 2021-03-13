package net.dodogang.sizzle.init;

import net.dodogang.sizzle.Sizzle;
import net.dodogang.sizzle.item.*;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.FallibleItemDispenserBehavior;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

@SuppressWarnings("unused")
public class SizzleItems {
    public static final Item WITHER_BONE_MEAL = register("wither_bone_meal", new WitherBoneMealItem(new Item.Settings().group(Sizzle.ITEM_GROUP)));
    public static final Item WITHER_BONE = register("wither_bone");

    public SizzleItems() {
        DispenserBlock.registerBehavior(SizzleItems.WITHER_BONE_MEAL, new FallibleItemDispenserBehavior() {
            @Override
            protected ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
                this.setSuccess(true);

                World world = pointer.getWorld();
                BlockPos blockPos = pointer.getBlockPos().offset(pointer.getBlockState().get(DispenserBlock.FACING));
                if (!WitherBoneMealItem.useOnDefertilizable(stack, world, blockPos)) this.setSuccess(false);

                return stack;
            }
        });
    }

    private static Item register(String id, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(Sizzle.MOD_ID, id), item);
    }
    private static Item register(String id) {
        return register(id, new Item(new Item.Settings().group(Sizzle.ITEM_GROUP)));
    }
}
