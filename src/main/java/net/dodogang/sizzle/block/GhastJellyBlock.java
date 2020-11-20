package net.dodogang.sizzle.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class GhastJellyBlock extends Block {
    public static final String id = "ghast_jelly_block";

    private boolean hasRecentlyBounced;

    public GhastJellyBlock(AbstractBlock.Settings properties) {
        super(properties);
    }

    @Override
    public void onLandedUpon(World world, BlockPos pos, Entity entity, float distance) {
        entity.fallDistance = 0;
    }

    @Override
    public void onEntityLand(BlockView world, Entity entity) {
        if (entity.getVelocity().length() > 0.4 && !entity.bypassesLandingEffects()) {
            Vec3d vec3d = entity.getVelocity();
            if (vec3d.y < 0) {
                double mul = entity instanceof LivingEntity ? 1 : 0.8;
                entity.setVelocity(vec3d.x, -vec3d.y * mul, vec3d.z);
            }
            return;
        }

        super.onEntityLand(world, entity);
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (entity.isOnGround() || (entity instanceof PlayerEntity && ((PlayerEntity)entity).abilities.flying)) return;
        boolean didRecentlyBounce = this.hasRecentlyBounced;

        if (!this.hasRecentlyBounced) {
            Vec3d vec3d = entity.getVelocity();
            entity.setVelocity(-vec3d.x * 2.0D, vec3d.y, -vec3d.z * 2.0D);
            this.hasRecentlyBounced = true;
        }

        if (didRecentlyBounce) this.hasRecentlyBounced = false;
    }

    @Override
    public boolean isSideInvisible(BlockState state, BlockState other, Direction dir) {
        return other.isOf(this);
    }

    private VoxelShape getShape() {
        return Block.createCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 15.0D, 15.0D);
    }
    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return this.getShape();
    }
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return this.getShape();
    }
}
