package net.dodogang.sizzle.api;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@FunctionalInterface
@OnlyIn(Dist.CLIENT)
public interface ISizzleServer extends ISizzle {
    static ISizzleServer get() {
        return (ISizzleServer) ISizzle.get();
    }
}
