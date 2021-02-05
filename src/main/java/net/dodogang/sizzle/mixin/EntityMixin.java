package net.dodogang.sizzle.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoulSandBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class EntityMixin {
    @SuppressWarnings("all")
    @Inject(method = "getVelocityMultiplier", at = @At("HEAD"), cancellable = true)
    private void getVelocityMultiplier(CallbackInfoReturnable<Float> cir) {
        Entity entity = Entity.class.cast(this);
        Block velocityAffectingBlock = entity.world.getBlockState(
            new BlockPos(
                entity.getPos().x,
                entity.getBoundingBox().minY - 0.5000001D,
                entity.getPos().z
            )
        ).getBlock();

        if (entity instanceof AbstractSkeletonEntity && velocityAffectingBlock instanceof SoulSandBlock) {
            Block block = entity.world.getBlockState(entity.getBlockPos()).getBlock();

            if (block != Blocks.WATER && block != Blocks.BUBBLE_COLUMN) cir.setReturnValue(1.0F);
                else cir.setReturnValue(block.getVelocityMultiplier());
        }
    }
}
