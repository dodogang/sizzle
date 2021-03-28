package net.dodogang.sizzle.block;

import net.dodogang.sizzle.tag.SizzleEntityTypeTags;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

@SuppressWarnings("deprecation")
public class GhastJellyBlock extends Block {
    public static final String id = "ghast_jelly_block";

    protected float pushSpeedReduction = 8.0F;

    public GhastJellyBlock(AbstractBlock.Settings properties) {
        super(properties);
    }

    @Override
    public void onLandedUpon(World world, BlockPos pos, Entity entity, float distance) {
        if (entity.bypassesLandingEffects()) {
            super.onLandedUpon(world, pos, entity, distance);
        } else {
            entity.handleFallDamage(distance, 0.0F);
        }
    }

    @Override
    public void onEntityLand(BlockView world, Entity entity) {
        if (entity.getVelocity().length() > 0.4D && !entity.bypassesLandingEffects()) {
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
        if (!entity.noClip && !entity.getType().isIn(SizzleEntityTypeTags.GHAST_JELLY_UNCOLLIDABLES)) {
            double deltaX = pos.getX() - entity.getX();
            double deltaZ = pos.getZ() - entity.getZ();
            double diffXZ = MathHelper.absMax(deltaX, deltaZ);
            if (diffXZ >= 0.009999999776482582D) {
                diffXZ = MathHelper.sqrt(diffXZ);
                deltaX /= diffXZ;
                deltaZ /= diffXZ;
                double velModifier = 1.0D / diffXZ;
                if (velModifier > 1.0D) {
                    velModifier = 1.0D;
                }

                deltaX *= velModifier;
                deltaZ *= velModifier;
                deltaX *= 0.05000000074505806D;
                deltaZ *= 0.05000000074505806D;
                deltaX *= 1.0F - this.pushSpeedReduction;
                deltaZ *= 1.0F - this.pushSpeedReduction;

                if (!entity.hasPassengers()) {
                    entity.addVelocity(deltaX, 0.0D, deltaZ);
                }
            }
        }
    }

    @Override
    public boolean isSideInvisible(BlockState state, BlockState other, Direction dir) {
        return other.isOf(this);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return Block.createCuboidShape(0.5D, 0.5D, 0.5D, 15.5D, 15.5D, 15.5D);
    }
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.fullCube();
    }
}
