package net.dodogang.sizzle.item;

import java.util.Random;

import net.dodogang.sizzle.tag.SizzleBlockTags;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
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
            Block newBlock = block;
            switch (Registry.BLOCK.getId(block).toString()) {
                case "minecraft:grass_block":
                case "minecraft:podzol":
                case "minecraft:mycelium":
                    newBlock = Blocks.DIRT;
                    break;
                case "minecraft:dirt":
                    newBlock = Blocks.COARSE_DIRT;
                    break;
                case "minecraft:grass":
                case "minecraft:tall_grass":
                case "minecraft:fern":
                case "minecraft:large_fern":
                    if (canPlantDeadBush(world.getBlockState(pos.down()).getBlock())) newBlock = Blocks.DEAD_BUSH;
                    break;
            }

            if (newBlock != block) {
                world.breakBlock(pos, false);
                world.setBlockState(pos, newBlock.getDefaultState());

                stack.decrement(1);
                return true;
            }
        }

        return false;
    }

    private static boolean canPlantDeadBush(Block block) {
        return block == Blocks.SAND || block == Blocks.RED_SAND
                || block == Blocks.TERRACOTTA || block == Blocks.WHITE_TERRACOTTA
                || block == Blocks.ORANGE_TERRACOTTA || block == Blocks.MAGENTA_TERRACOTTA
                || block == Blocks.LIGHT_BLUE_TERRACOTTA || block == Blocks.YELLOW_TERRACOTTA
                || block == Blocks.LIME_TERRACOTTA || block == Blocks.PINK_TERRACOTTA
                || block == Blocks.GRAY_TERRACOTTA || block == Blocks.LIGHT_GRAY_TERRACOTTA
                || block == Blocks.CYAN_TERRACOTTA || block == Blocks.PURPLE_TERRACOTTA
                || block == Blocks.BLUE_TERRACOTTA || block == Blocks.BROWN_TERRACOTTA
                || block == Blocks.GREEN_TERRACOTTA || block == Blocks.RED_TERRACOTTA
                || block == Blocks.BLACK_TERRACOTTA || block == Blocks.DIRT
                || block == Blocks.COARSE_DIRT || block == Blocks.PODZOL;
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
