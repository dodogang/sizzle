package net.dodogang.sizzle.init;

import net.dodogang.sizzle.Sizzle;
import net.dodogang.sizzle.particle.vanilla.PublicDefaultParticleType;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SizzleParticles {
    public static final DefaultParticleType MAGMA_TONGUE = register("magma_tongue", false);

    public SizzleParticles() {}

    private static DefaultParticleType register(String id, boolean alwaysShow) {
      return Registry.register(Registry.PARTICLE_TYPE, new Identifier(Sizzle.MOD_ID, id), new PublicDefaultParticleType(alwaysShow));
   }
}
