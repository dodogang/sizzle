package net.dodogang.sizzle.api.plugin;

import net.dodogang.sizzle.api.ISizzle;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;

/**
 * Plugins implementing this interface receive Sizzle lifecycle updates. Because Forge loads mods using multiple
 * threads, using the FML lifecycle events of your mod may cause concurrent issues when accessing Nature's Debris. To
 * ensure Sizzle is in a correct state for loading your plugin, use this listener instead.
 */
public interface ISizzleLifecycleListener {

    /**
     * Called after Sizzle finished construction-time loading. More specifically: this method is called once the Sizzle
     * has finished loading in the mod class constructor.
     *
     * @param sizzle The Sizzle instance
     */
    default void sizzleConstruct(ISizzle sizzle) {
    }

    /**
     * Called after Sizzle finished setup-time loading. More specifically: this method is called once the Sizzle has
     * finished loading during the {@link FMLCommonSetupEvent}.
     *
     * @param sizzle The Sizzle instance
     */
    default void sizzleSetup(ISizzle sizzle) {
    }

    /**
     * Called after Sizzle finished completion-time loading. More specifically: this method is called once the Sizzle
     * has finished loading during the {@link FMLLoadCompleteEvent}.
     *
     * @param sizzle The Sizzle instance
     */
    default void sizzleLoaded(ISizzle sizzle) {
    }
}
