package net.dodogang.sizzle.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.dodogang.sizzle.block.CapBedBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(method = "setPositionInBed", at = @At("HEAD"), cancellable = true)
    private void setPositionInBed(BlockPos pos, CallbackInfo ci) {
        LivingEntity entity = LivingEntity.class.cast(this);
        if (entity.world.getBlockState(pos).getBlock() instanceof CapBedBlock) {
            entity.updatePosition(pos.getX() + 0.5D, pos.getY() + 1.0D, pos.getZ() + 0.5D);
            ci.cancel();
        }
    }
}
