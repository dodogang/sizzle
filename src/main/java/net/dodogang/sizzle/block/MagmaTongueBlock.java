package net.dodogang.sizzle.block;

import net.dodogang.sizzle.init.SizzleParticles;
import net.dodogang.sizzle.state.property.SizzleProperties;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

import java.util.Objects;
import java.util.Random;

@SuppressWarnings("deprecation")
public class MagmaTongueBlock extends PlantBlock implements Waterloggable {
    public static final String id = "magma_tongue";

    public static final IntProperty TONGUES = SizzleProperties.TONGUES;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    public MagmaTongueBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(TONGUES, 1).with(WATERLOGGED, false));
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState blockState = ctx.getWorld().getBlockState(ctx.getBlockPos());
        if (blockState.isOf(this)) {
            return blockState.with(TONGUES, Math.min(5, blockState.get(TONGUES) + 1));
        } else {
            FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
            boolean waterlogged = fluidState.getFluid() == Fluids.WATER;
            return Objects.requireNonNull(super.getPlacementState(ctx)).with(WATERLOGGED, waterlogged);
        }
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return !floor.getCollisionShape(world, pos).getFace(Direction.UP).isEmpty() || floor.isSideSolidFullSquare(world, pos, Direction.UP);
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockPos blockPos = pos.down();
        return this.canPlantOnTop(world.getBlockState(blockPos), world, blockPos);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState newState, WorldAccess world, BlockPos pos, BlockPos posFrom) {
        if (!state.canPlaceAt(world, pos)) {
            return Blocks.AIR.getDefaultState();
        } else {
            if (state.get(WATERLOGGED)) {
                world.getFluidTickScheduler().schedule(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
            }

            return super.getStateForNeighborUpdate(state, direction, newState, world, pos, posFrom);
        }
    }

    @Override
    public boolean canReplace(BlockState state, ItemPlacementContext context) {
        return context.getStack().getItem() == this.asItem() && state.get(TONGUES) < 5;
    }

    @Environment(EnvType.CLIENT)
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        PlayerEntity player = MinecraftClient.getInstance().player;
        if (player != null && player.isCreative()) {
            return VoxelShapes.fullCube();
        } else {
            Vec3d vec3d = state.getModelOffset(world, pos);
            return ShapeUtil.getShapeForTongues(state.get(TONGUES)).offset(vec3d.x, vec3d.y, vec3d.z);
        }
    }

    @Override
    public AbstractBlock.OffsetType getOffsetType() {
        return OffsetType.XZ;
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(TONGUES, WATERLOGGED);
    }

    @Environment(EnvType.CLIENT)
    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (!state.get(WATERLOGGED) && random.nextInt(5) <= (state.get(TONGUES))) {
            int j = random.nextInt(2) * 2 - 1;
            int k = random.nextInt(2) * 2 - 1;
            double x = pos.getX() + 0.5D + 0.25D * j;
            double y = pos.getY() + random.nextFloat();
            double z = pos.getZ() + 0.5D + 0.25D * k;

            world.addParticle(SizzleParticles.MAGMA_TONGUE, x, y, z, 0.0D, 0.0D, 0.0D);
        }
    }

    protected static class ShapeUtil {
        protected static final VoxelShape SH_SMALLER_TONGUE = VoxelShapes.union(
            Block.createCuboidShape(1.0D, 0.0D, 1.0D, 3.0D, 3.0D, 3.0D),
            Block.createCuboidShape(0.0D, 2.0D, 0.0D, 4.0D, 6.0D, 4.0D)
        );
        protected static final VoxelShape SH_SMALL_TONGUE = VoxelShapes.union(
            Block.createCuboidShape(1.0D, 0.0D, 1.0D, 3.0D, 3.0D, 3.0D),
            Block.createCuboidShape(0.0D, 2.0D, 0.0D, 4.0D, 7.0D, 4.0D)
        );
        protected static final VoxelShape SH_TONGUE = VoxelShapes.union(
            Block.createCuboidShape(1.0D, 0.0D, 1.0D, 3.0D, 4.0D, 3.0D),
            Block.createCuboidShape(0.0D, 4.0D, 0.0D, 4.0D, 10.0D, 4.0D)
        );
        protected static final VoxelShape SH_TALL_TONGUE = VoxelShapes.union(
            Block.createCuboidShape(1.0D, 0.0D, 1.0D, 3.0D, 8.0D, 3.0D),
            Block.createCuboidShape(0.0D, 8.0D, 0.0D, 4.0D, 13.0D, 4.0D)
        );
        protected static final VoxelShape SH_LARGE_TONGUE = VoxelShapes.union(
            Block.createCuboidShape(1.0D, 0.0D, 1.0D, 4.0D, 6.0D, 4.0D),
            Block.createCuboidShape(0.0D, 6.0D, 0.0D, 5.0D, 14.0D, 5.0D)
        );
        protected static final VoxelShape SH_LARGER_TONGUE = VoxelShapes.union(
            Block.createCuboidShape(1.0D, 0.0D, 1.0D, 4.0D, 7.0D, 4.0D),
            Block.createCuboidShape(0.0D, 7.0D, 0.0D, 5.0D, 16.0D, 5.0D)
        );

        protected static final VoxelShape ONE_TONGUE = create(SH_TONGUE, 6.0D, 7.0D);
        protected static final VoxelShape TWO_TONGUES = VoxelShapes.union(create(SH_SMALLER_TONGUE, 4.0D, 3.0D), create(SH_TONGUE, 9.0D, 8.0D));
        protected static final VoxelShape THREE_TONGUES = VoxelShapes.union(create(SH_SMALLER_TONGUE, 9.0D, 3.0D), create(SH_TONGUE, 3.0D, 7.0D), create(SH_LARGE_TONGUE, 8.0D, 10.0D));
        protected static final VoxelShape FOUR_TONGUES = VoxelShapes.union(create(SH_SMALL_TONGUE, 10.0D, 3.0D), create(SH_SMALL_TONGUE, 8.0D, 11.0D), create(SH_TALL_TONGUE, 2.0D, 10.0D), create(SH_LARGER_TONGUE, 4.0D, 3.0D));
        protected static final VoxelShape FIVE_TONGUES = VoxelShapes.union(create(SH_SMALL_TONGUE, 10.0D, 3.0D), create(SH_SMALL_TONGUE, 7.0D, 7.0D), create(SH_TALL_TONGUE, 0.0D, 9.0D), create(SH_LARGER_TONGUE, 2.0D, 2.0D), create(SH_LARGER_TONGUE, 10.0D, 9.0D));

        protected static VoxelShape getShapeForTongues(int tongues) {
            switch (tongues) {
                case 1:
                default:
                    return ONE_TONGUE;
                case 2:
                    return TWO_TONGUES;
                case 3:
                    return THREE_TONGUES;
                case 4:
                    return FOUR_TONGUES;
                case 5:
                    return FIVE_TONGUES;
            }
        }

        protected static VoxelShape create(VoxelShape shape, double x, double z) {
            return shape.offset(x / 16, 0.0D, z / 16);
        }
    }
}
