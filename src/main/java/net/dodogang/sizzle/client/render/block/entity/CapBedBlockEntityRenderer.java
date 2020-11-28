package net.dodogang.sizzle.client.render.block.entity;

import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import net.dodogang.sizzle.Sizzle;
import net.dodogang.sizzle.block.entity.CapBedBlockEntity;
import net.dodogang.sizzle.init.SizzleBlockEntities;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BedBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.DoubleBlockProperties;
import net.minecraft.block.DoubleBlockProperties.PropertySource;
import net.minecraft.block.enums.BedPart;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.LightmapCoordinatesRetriever;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

@Environment(EnvType.CLIENT)
public class CapBedBlockEntityRenderer extends BlockEntityRenderer<CapBedBlockEntity> {
    private final ModelPart field_20813 = new ModelPart(64, 64, 0, 0);
    private final ModelPart field_20814;
    private final ModelPart[] legs = new ModelPart[4];

    public CapBedBlockEntityRenderer(BlockEntityRenderDispatcher blockEntityRenderDispatcher) {
        super(blockEntityRenderDispatcher);
        this.field_20813.addCuboid(0.0F, 0.0F, 0.0F, 16.0F, 16.0F, 6.0F, 0.0F);
        this.field_20814 = new ModelPart(64, 64, 0, 22);
        this.field_20814.addCuboid(0.0F, 0.0F, 0.0F, 16.0F, 16.0F, 6.0F, 0.0F);
        this.legs[0] = new ModelPart(64, 64, 50, 0);
        this.legs[1] = new ModelPart(64, 64, 50, 6);
        this.legs[2] = new ModelPart(64, 64, 50, 12);
        this.legs[3] = new ModelPart(64, 64, 50, 18);
        this.legs[0].addCuboid(0.0F, 6.0F, -16.0F, 3.0F, 3.0F, 3.0F);
        this.legs[1].addCuboid(0.0F, 6.0F, 0.0F, 3.0F, 3.0F, 3.0F);
        this.legs[2].addCuboid(-16.0F, 6.0F, -16.0F, 3.0F, 3.0F, 3.0F);
        this.legs[3].addCuboid(-16.0F, 6.0F, 0.0F, 3.0F, 3.0F, 3.0F);
        this.legs[0].pitch = 1.5707964F;
        this.legs[1].pitch = 1.5707964F;
        this.legs[2].pitch = 1.5707964F;
        this.legs[3].pitch = 1.5707964F;
        this.legs[0].roll = 0.0F;
        this.legs[1].roll = 1.5707964F;
        this.legs[2].roll = 4.712389F;
        this.legs[3].roll = 3.1415927F;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public void render(CapBedBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        SpriteIdentifier spriteIdentifier = new SpriteIdentifier(TexturedRenderLayers.BEDS_ATLAS_TEXTURE, new Identifier(Sizzle.MOD_ID, "entity/bed/" + entity.getId()));
        World world = entity.getWorld();

        if (world != null) {
            BlockState blockState = entity.getCachedState();
            PropertySource<CapBedBlockEntity> propertySource = DoubleBlockProperties
                    .toPropertySource(SizzleBlockEntities.CAP_BED, BedBlock::getBedPart, BedBlock::getOppositePartDirection,
                            ChestBlock.FACING, blockState, world, entity.getPos(), (worldAccess, blockPos) -> {
                                return false;
                            });
            int k = ((Int2IntFunction) propertySource.apply(new LightmapCoordinatesRetriever())).get(light);
            this.method_3558(matrices, vertexConsumers, blockState.get(BedBlock.PART) == BedPart.HEAD, blockState.get(BedBlock.FACING), spriteIdentifier, k, overlay, false);
        } else {
            this.method_3558(matrices, vertexConsumers, true, Direction.SOUTH, spriteIdentifier, light, overlay, false);
            this.method_3558(matrices, vertexConsumers, false, Direction.SOUTH, spriteIdentifier, light, overlay, true);
        }
    }

    private void method_3558(MatrixStack matrix, VertexConsumerProvider vertexConsumerProvider, boolean visible, Direction direction, SpriteIdentifier spriteIdentifier, int light, int overlay, boolean bl2) {
        this.field_20813.visible = visible;
        this.field_20814.visible = !visible;
        this.legs[0].visible = !visible;
        this.legs[1].visible = visible;
        this.legs[2].visible = !visible;
        this.legs[3].visible = visible;
        matrix.push();
        matrix.translate(0.0D, 0.5625D, bl2 ? -1.0D : 0.0D);
        matrix.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion(90.0F));
        matrix.translate(0.5D, 0.5D, 0.5D);
        matrix.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion(180.0F + direction.asRotation()));
        matrix.translate(-0.5D, -0.5D, -0.5D);
        VertexConsumer vertexConsumer = spriteIdentifier.getVertexConsumer(vertexConsumerProvider, RenderLayer::getEntitySolid);
        this.field_20813.render(matrix, vertexConsumer, light, overlay);
        this.field_20814.render(matrix, vertexConsumer, light, overlay);
        this.legs[0].render(matrix, vertexConsumer, light, overlay);
        this.legs[1].render(matrix, vertexConsumer, light, overlay);
        this.legs[2].render(matrix, vertexConsumer, light, overlay);
        this.legs[3].render(matrix, vertexConsumer, light, overlay);
        matrix.pop();
    }
}
