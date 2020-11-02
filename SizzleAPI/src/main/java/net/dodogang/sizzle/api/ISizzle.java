package net.dodogang.sizzle.api;

import net.minecraftforge.eventbus.api.IEventBus;

public interface ISizzle {
    /**
     * Gets Sizzle mod instance. This is {@code net.dodogang.sizzle.common.SizzleServer} on dedicated server dist and
     * {@code net.dodogang.sizzle.client.SizzleClient} on the client dist.
     * <p>
     * On the client dist, this instance can be cast to {@link ISizzleClient}, although the {@link ISizzleClient} can be
     * more easily obtained via {@link ISizzleClient#get()}.
     * </p>
     * <p>
     * On the server dist, this instance can be cast to {@link ISizzleServer}, although the {@link ISizzleServer} can be
     * more easily obtained via {@link ISizzleServer#get()}.
     * </p>
     */
    static ISizzle get() {
        return APIDelegate.get();
    }

    /**
     * Returns Sizzle event bus, to register for Sizzle-specific events. Note that this event bus is neither the Forge
     * or the FML event bus.
     */
    static IEventBus eventBus() {
        return APIDelegate.EVENT_BUS;
    }

    /**
     * Returns the version info of the Sizzle mod.
     */
    ISizzleInfo info();
}
