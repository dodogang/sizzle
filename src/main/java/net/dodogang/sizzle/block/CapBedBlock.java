package net.dodogang.sizzle.block;

import net.dodogang.sizzle.block.entity.CapBedBlockEntity;
import net.minecraft.block.BedBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class CapBedBlock extends BedBlock {
    private String id;

    public CapBedBlock(String id, DyeColor dyeColor, Settings settings) {
        super(dyeColor, settings);
        this.id = id;
    }

    @Override
    public BlockEntity createBlockEntity(BlockView world) {
        return new CapBedBlockEntity(this.id);
    }

    @Override
    public void onLandedUpon(World world, BlockPos pos, Entity entity, float distance) {
        entity.handleFallDamage(distance, 1.0F);
    }

    @Override
    public void onEntityLand(BlockView world, Entity entity) {
        entity.setVelocity(entity.getVelocity().multiply(1.0D, 0.0D, 1.0D));
    }
}
