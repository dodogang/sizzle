package net.dodogang.sizzle.core;

import org.spongepowered.asm.mixin.Mixins;
import org.spongepowered.asm.mixin.connect.IMixinConnector;

public class SizzleMixinConnector implements IMixinConnector {
    @Override
    public void connect() {
        Mixins.addConfiguration("sizzle.mixins.json");
    }
}
