package net.dodogang.sizzle.api;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@FunctionalInterface
@OnlyIn(Dist.CLIENT)
public interface ISizzleClient extends ISizzle {
    static ISizzleClient get() {
        return (ISizzleClient) ISizzle.get();
    }
}
