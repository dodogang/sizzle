package net.dodogang.sizzle;

import static net.dodogang.sizzle.Sizzle.log;

import net.dodogang.sizzle.client.particle.MagmaTongueParticle;
import net.dodogang.sizzle.init.SizzleBlocks;
import net.dodogang.sizzle.init.SizzleParticles;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.client.render.RenderLayer;

public class SizzleClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        log("Initializing client");

        ParticleFactoryRegistry.getInstance().register(SizzleParticles.MAGMA_TONGUE, MagmaTongueParticle.Factory::new);
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), SizzleBlocks.TALL_CRIMSON_ROOTS, SizzleBlocks.TALLER_CRIMSON_ROOTS, SizzleBlocks.TALL_WARPED_ROOTS, SizzleBlocks.TALLER_WARPED_ROOTS);

        log("Initialized client");
    }
}
