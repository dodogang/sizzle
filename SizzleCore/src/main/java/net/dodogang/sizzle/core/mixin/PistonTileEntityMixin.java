package net.dodogang.sizzle.core.mixin;

import net.dodogang.sizzle.core.block.IPistonEntityMovingBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.PistonTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PistonTileEntity.class)
public abstract class PistonTileEntityMixin extends TileEntity {
    private PistonTileEntityMixin(TileEntityType<?> type) {
        super(type);
    }

    @Shadow
    protected abstract boolean isPushingHoneyBlock();

    @Shadow
    public abstract Direction getMotionDirection();

    @Shadow private BlockState pistonState;

    @Shadow
    protected abstract AxisAlignedBB moveByPositionAndProgress(AxisAlignedBB box);

    @Shadow private float progress;

    @Shadow
    private static boolean method_23671(AxisAlignedBB box, Entity entity) {
        return false;
    }

    @Shadow
    private static void method_23672(Direction direction, Entity entity, double d, Direction facing) {
    }

    @Shadow private boolean extending;

    @Inject(method = "method_23674", at = @At("HEAD"))
    private void onPistonMoveEntities(float last, CallbackInfo info) {
        assert world != null;

        Block block = pistonState.getBlock();
        System.out.println(block);

        if (block instanceof IPistonEntityMovingBlock) {
            IPistonEntityMovingBlock mblock = (IPistonEntityMovingBlock) block;
            System.out.println(extending);
            System.out.println(mblock.movesEntitiesOutwards());
            System.out.println(mblock.movesEntitiesInwards());
            System.out.println(!extending && mblock.movesEntitiesInwards());
            System.out.println(extending && mblock.movesEntitiesOutwards());
            System.out.println(extending && mblock.movesEntitiesOutwards() || !extending && mblock.movesEntitiesInwards());
            if (extending && mblock.movesEntitiesOutwards() || !extending && mblock.movesEntitiesInwards()) {
                Direction motionDirection = getMotionDirection();
                AxisAlignedBB box = moveByPositionAndProgress(new AxisAlignedBB(0, 0, 0, 1, 1.5, 1));
                double toGo = last - progress;

                for (Entity entity : world.getEntitiesInAABBexcluding(null, box, entity -> true)) {//method_23671(box, entity)
                    method_23672(motionDirection, entity, toGo, motionDirection);
                }
            }
        }
    }
}
