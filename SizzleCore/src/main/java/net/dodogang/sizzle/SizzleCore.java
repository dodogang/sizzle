package net.dodogang.sizzle;

import net.dodogang.sizzle.api.ISizzle;
import net.dodogang.sizzle.api.ISizzleInfo;

public abstract class SizzleCore implements ISizzle {
    @Override
    public ISizzleInfo info() {
        return SizzleInfo.INSTANCE;
    }
}
