package net.dodogang.sizzle.item;

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
import net.minecraft.util.math.Direction;
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
        if (count == 0) count = 15;

        BlockState blockState = world.getBlockState(pos);
        if (!blockState.isAir()) {
            double d = 0.5D;
            double g;
            if (blockState.isOf(Blocks.WATER)) {
                count *= 3;
                g = 1.0D;
                d = 3.0D;
            } else if (blockState.isOpaqueFullCube(world, pos)) {
                pos = pos.up();
                count *= 3;
                d = 3.0D;
                g = 1.0D;
            } else {
                g = blockState.getOutlineShape(world, pos).getMax(Direction.Axis.Y);
            }

            world.addParticle(ParticleTypes.SMOKE, (double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D,
                    (double) pos.getZ() + 0.5D, 0.0D, 0.0D, 0.0D);

            for (int i = 0; i < count; ++i) {
                double velocityX = RANDOM.nextGaussian() * 0.02D;
                double velocityY = RANDOM.nextGaussian() * 0.02D;
                double velocityZ = RANDOM.nextGaussian() * 0.02D;
                double l = 0.5D - d;
                double x = (double) pos.getX() + l + RANDOM.nextDouble() * d * 2.0D;
                double y = (double) pos.getY() + RANDOM.nextDouble() * g;
                double z = (double) pos.getZ() + l + RANDOM.nextDouble() * d * 2.0D;
                if (!world.getBlockState((new BlockPos(x, y, z)).down()).isAir()) {
                    world.addParticle(ParticleTypes.SMOKE, x, y, z, velocityX, velocityY, velocityZ);
                }
            }

        }
    }
}
