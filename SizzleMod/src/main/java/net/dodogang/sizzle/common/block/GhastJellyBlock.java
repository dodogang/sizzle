package net.dodogang.sizzle.common.block;

import net.dodogang.sizzle.core.block.IPistonEntityMovingBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class GhastJellyBlock extends Block implements IPistonEntityMovingBlock {
    private static final VoxelShape SHAPE = makeCuboidShape(1, 0, 1, 15, 15, 15);
    private static final VoxelShape FULL_SHAPE = makeCuboidShape(0, 0, 0, 16, 16, 16);

    public GhastJellyBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void onFallenUpon(World world, BlockPos pos, Entity entity, float fallDist) {
        entity.fallDistance = 0;
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getCollisionShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext ctx) {
        if (ctx.getEntity() != null) {
            if (ctx.getEntity().getMotion().length() >= 0.4 && !ctx.getEntity().bypassesLandingEffects()) {
                return FULL_SHAPE;
            }
            if (ctx.getEntity().getMotion().y < 0 && !ctx.getEntity().bypassesLandingEffects()) {
                return SHAPE;
            }
        }
        return VoxelShapes.empty();
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext ctx) {
        if (ctx.isDescending())
            return VoxelShapes.empty();
        return FULL_SHAPE;
    }

    @Override
    public void onLanded(IBlockReader world, Entity entity) {
        if (entity.getMotion().length() > 0.4 && !entity.bypassesLandingEffects()) {
            Vector3d motion = entity.getMotion();
            if (motion.y < 0) {
                double mul = entity instanceof LivingEntity ? 1 : 0.8;
                entity.setMotion(motion.x, -motion.y * mul, motion.z);
            }
            return;
        }
        super.onLanded(world, entity);
    }

    @Override
    public boolean canStickTo(BlockState state, BlockState other) {
        return !state.isIn(Blocks.SLIME_BLOCK) && !state.isIn(Blocks.HONEY_BLOCK);
    }

    @Override
    public boolean isStickyBlock(BlockState state) {
        return true;
    }

    private static final double BLOCK_SHAPE_HEIGHT = 15 / 16d;
    private static final double BLOCK_SHAPE_RADIUS = 7 / 16d;

    @Override
    @SuppressWarnings("deprecation")
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
//        Vector3d motion = entity.getMotion();
//        double destVelo = entity.isSneaking() ? -0.01 : 0.12;
//        if (motion.y < destVelo && entity.getY() - pos.getY() < BLOCK_SHAPE_HEIGHT - 1e-7) {
//            double distToMaxYMotion = destVelo - motion.y;
//            entity.setMotion(motion.x, motion.y * 0.6 + distToMaxYMotion, motion.z);
//        }
//        entity.fallDistance = 0;
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isSideInvisible(BlockState state, BlockState other, Direction dir) {
        return other.isIn(this);
    }

    @Override
    public boolean movesEntitiesOutwards() {
        return false;
    }

    @Override
    public boolean movesEntitiesInwards() {
        return true;
    }
}
