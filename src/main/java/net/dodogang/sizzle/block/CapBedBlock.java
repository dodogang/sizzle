package net.dodogang.sizzle.block;

import net.minecraft.block.BedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class CapBedBlock extends BedBlock {
    private boolean hasLayer;

    protected static final VoxelShape LAYER_SHAPE = Block.createCuboidShape(0.0D, 10.0D, 0.0D, 16.0D, 13.0D, 16.0D);
    protected static final VoxelShape TOP_SHAPE = Block.createCuboidShape(0.0D, 5.0D, 0.0D, 16.0D, 10.0D, 16.0D);
    protected static final VoxelShape LEG_1_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 4.0D, 5.0D, 4.0D);
    protected static final VoxelShape LEG_2_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 12.0D, 4.0D, 5.0D, 16.0D);
    protected static final VoxelShape LEG_3_SHAPE = Block.createCuboidShape(12.0D, 0.0D, 0.0D, 16.0D, 5.0D, 4.0D);
    protected static final VoxelShape LEG_4_SHAPE = Block.createCuboidShape(12.0D, 0.0D, 12.0D, 16.0D, 5.0D, 16.0D);
    protected static final VoxelShape NORTH_SHAPE = VoxelShapes.union(TOP_SHAPE, LEG_1_SHAPE, LEG_3_SHAPE);
    protected static final VoxelShape SOUTH_SHAPE = VoxelShapes.union(TOP_SHAPE, LEG_2_SHAPE, LEG_4_SHAPE);
    protected static final VoxelShape WEST_SHAPE = VoxelShapes.union(TOP_SHAPE, LEG_1_SHAPE, LEG_2_SHAPE);
    protected static final VoxelShape EAST_SHAPE = VoxelShapes.union(TOP_SHAPE, LEG_3_SHAPE, LEG_4_SHAPE);

    public CapBedBlock(boolean hasLayer, Settings settings) {
        super(DyeColor.WHITE, settings);
        this.hasLayer = hasLayer;
    }
    public CapBedBlock(Settings settings) {
        this(false, settings);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction direction = getOppositePartDirection(state).getOpposite();
        VoxelShape voxelShape;

        switch (direction) {
            case NORTH:
                voxelShape = NORTH_SHAPE;
                break;
            case SOUTH:
                voxelShape = SOUTH_SHAPE;
                break;
            case WEST:
                voxelShape = WEST_SHAPE;
                break;
            default:
                voxelShape = EAST_SHAPE;
                break;
        }

        return !this.hasLayer ? voxelShape : VoxelShapes.union(voxelShape, LAYER_SHAPE);
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
