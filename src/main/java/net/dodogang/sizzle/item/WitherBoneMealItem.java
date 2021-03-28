package net.dodogang.sizzle.item;

import java.util.Random;

import net.dodogang.sizzle.tag.SizzleBlockTags;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

public class WitherBoneMealItem extends Item {
    public WitherBoneMealItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();

        if (useOnDefertilizable(context.getStack(), world, pos)) {
            if (world.isClient) createParticles(world, pos, 32);
            return ActionResult.success(true);
        }

        return ActionResult.PASS;
    }

    public static boolean useOnDefertilizable(ItemStack stack, World world, BlockPos pos) {
        Block block = world.getBlockState(pos).getBlock();

        if (block.isIn(SizzleBlockTags.WITHER_BONE_MEAL_DEFERTILIZABLE)) {
            switch (Registry.BLOCK.getId(block).toString()) {
                case "minecraft:grass_block":
                case "minecraft:podzol":
                case "minecraft:mycelium":
                    block = Blocks.DIRT;
                    break;
                case "minecraft:dirt":
                    block = Blocks.COARSE_DIRT;
                    break;
                default:
                    block = Blocks.DEAD_BUSH;
                    break;
            }

            BlockState blockState = block.getDefaultState();
            if (canPlaceAt(blockState, world, pos)) {
                world.breakBlock(pos, false);
                world.setBlockState(pos, blockState);

                stack.decrement(1);

                return true;
            }
        }

        return false;
    }

    private static boolean canPlaceAt(BlockState blockState, WorldView world, BlockPos pos) {
        return blockState.canPlaceAt(world, pos);
    }

    @Environment(EnvType.CLIENT)
    public static void createParticles(WorldAccess world, BlockPos pos, int count) {
        Random random = world.getRandom();
        for (int i = 0; i < count; i++) {
            double x = pos.getX() + 0.5D + 0.25D * (random.nextInt(2) * 2 - 1);
            double y = (pos.getY() + random.nextFloat());
            double z = pos.getZ() + 0.5D + 0.25D * (random.nextInt(2) * 2 - 1);
            world.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0D, 0.0D, 0.0D);
        }
    }
}
