package net.dodogang.sizzle;

import static net.dodogang.sizzle.Sizzle.log;

import net.dodogang.sizzle.client.particle.MagmaTongueParticle;
import net.dodogang.sizzle.client.render.block.entity.CapBedBlockEntityRenderer;
import net.dodogang.sizzle.init.SizzleBlockEntities;
import net.dodogang.sizzle.init.SizzleBlocks;
import net.dodogang.sizzle.init.SizzleParticles;
import net.dodogang.sizzle.registry.SpriteIdentifierRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.Identifier;

public class SizzleClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        log("Initializing client");

        ParticleFactoryRegistry.getInstance().register(SizzleParticles.MAGMA_TONGUE, MagmaTongueParticle.Factory::new);
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), SizzleBlocks.TALL_CRIMSON_ROOTS, SizzleBlocks.TALLER_CRIMSON_ROOTS, SizzleBlocks.TALL_WARPED_ROOTS, SizzleBlocks.TALLER_WARPED_ROOTS);

        for (String string : new String[]{ "brown_cap", "red_cap", "rand_cap" }) {
            SpriteIdentifierRegistry.INSTANCE.addIdentifier(new SpriteIdentifier(TexturedRenderLayers.BEDS_ATLAS_TEXTURE, new Identifier(Sizzle.MOD_ID, "entity/bed/" + string)));
        }

        BlockEntityRendererRegistry.INSTANCE.register(SizzleBlockEntities.CAP_BED, (blockEntityRenderDispatcher -> new CapBedBlockEntityRenderer(BlockEntityRenderDispatcher.INSTANCE)));

        log("Initialized client");
    }
}
