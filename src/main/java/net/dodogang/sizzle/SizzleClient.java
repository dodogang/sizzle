package net.dodogang.sizzle;

import static net.dodogang.sizzle.Sizzle.log;

import net.dodogang.sizzle.client.particle.MagmaTongueParticle;
import net.dodogang.sizzle.init.SizzleParticles;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;

public class SizzleClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        log("Initializing client");

        ParticleFactoryRegistry.getInstance().register(SizzleParticles.MAGMA_TONGUE, MagmaTongueParticle.Factory::new);

        log("Initialized client");
    }
}
