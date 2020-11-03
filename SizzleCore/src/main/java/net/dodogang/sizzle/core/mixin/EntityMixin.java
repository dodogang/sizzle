package net.dodogang.sizzle.core.mixin;

import it.unimi.dsi.fastutil.objects.Object2DoubleMap;
import it.unimi.dsi.fastutil.objects.Object2DoubleOpenHashMap;
import net.dodogang.sizzle.api.ISizzle;
import net.dodogang.sizzle.core.entity.EntityGravityEvent;
import net.dodogang.sizzle.core.entity.EntityInMaterialEvent;
import net.dodogang.sizzle.core.entity.IGhastJellyInteractive;
import net.dodogang.sizzle.util.TypeUtil;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.fluid.Fluid;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.tags.ITag;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin implements IGhastJellyInteractive {
    private boolean inGhastJelly;

    private final Object2DoubleMap<Material> materialHeight = new Object2DoubleOpenHashMap<>();

    @Override
    public boolean isInGhastJelly() {
        return inGhastJelly;
    }

    @Override
    public void setInGhastJelly() {
        inGhastJelly = true;
    }

    @Override
    public void setNotInGhastJelly() {
        inGhastJelly = false;
    }

    @Shadow
    @Final
    private static DataParameter<Boolean> NO_GRAVITY;

    @Shadow
    public abstract AxisAlignedBB getBoundingBox();

    @Shadow
    public World world;

    @Shadow
    public abstract boolean isPushedByWater();

    @Shadow
    public abstract Vector3d getMotion();

    @Shadow
    public abstract void setMotion(Vector3d motion);

    @Shadow
    public abstract void setMotion(double x, double y, double z);

    @Shadow
    protected Object2DoubleMap<ITag<Fluid>> fluidHeight;

    @Shadow
    public abstract boolean isSneaking();

    @Shadow
    public abstract boolean isDescending();

    @Shadow
    public abstract boolean bypassesLandingEffects();

    @Shadow
    public abstract double getX();
    @Shadow
    public abstract double getY();
    @Shadow
    public abstract double getZ();

    @Shadow
    @Final
    protected EntityDataManager dataManager;

    @Shadow
    public abstract boolean hasNoGravity();

    @SuppressWarnings("deprecation")
    public boolean isInMaterial(Material material) {
        AxisAlignedBB aabb = getBoundingBox().shrink(0.001);

        int minx = MathHelper.floor(aabb.minX);
        int maxx = MathHelper.ceil(aabb.maxX);
        int miny = MathHelper.floor(aabb.minY);
        int maxy = MathHelper.ceil(aabb.maxY);
        int minz = MathHelper.floor(aabb.minZ);
        int maxz = MathHelper.ceil(aabb.maxZ);

        if (!world.isAreaLoaded(minx, miny, minz, maxx, maxy, maxz)) {
            return false;
        } else {
            boolean inMaterial = false;
            BlockPos.Mutable mpos = new BlockPos.Mutable();

            double submergedHeight = 0;

            for (int x = minx; x < maxx; ++x) {
                for (int y = miny; y < maxy; ++y) {
                    for (int z = minz; z < maxz; ++z) {
                        mpos.setPos(x, y, z);

                        BlockState state = world.getBlockState(mpos);
                        if (state.getMaterial() == material) {
                            double blockHeight = y + 1;
                            if (blockHeight >= aabb.minY) {
                                submergedHeight = Math.max(submergedHeight, blockHeight - aabb.minY);
                                inMaterial = true;
                            }
                        }
                    }
                }
            }

            materialHeight.put(material, submergedHeight);

            return inMaterial;
        }
    }

    @Inject(method = "hasNoGravity", at = @At("HEAD"), cancellable = true)
    private void onHasNoGravity(CallbackInfoReturnable<Boolean> info) {
        EntityGravityEvent event = new EntityGravityEvent(TypeUtil.cast(this, Entity.class));
        if (ISizzle.eventBus().post(event)) {
            info.setReturnValue(!event.getGravityEnabled());
        }
    }

    // We don't want temporary no-gravity to be written, delegate to NO_GRAVITY instead
    @Inject(method = "writeWithoutTypeId", at = @At("RETURN"))
    private void onWrite(CallbackInfoReturnable<CompoundNBT> info) {
        CompoundNBT nbt = info.getReturnValue();

        nbt.remove("NoGravity");
        if (dataManager.get(NO_GRAVITY))
            nbt.putBoolean("NoGravity", true);
    }

    @Inject(
        method = "baseTick",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/entity/Entity;updateWaterState()Z",
            shift = At.Shift.BEFORE
        )
    )
    private void updateMaterialState(CallbackInfo info) {
        Entity self = TypeUtil.cast(this, Entity.class);
        ISizzle.eventBus().post(new EntityInMaterialEvent(self, this::isInMaterial, materialHeight::getDouble));
    }
}
