/*
 * Copyright (c) 2020 RedGalaxy & contributors
 * All rights reserved. Do not distribute.
 *
 * For a full license, see LICENSE.txt
 */

package net.dodogang.sizzle;

import net.dodogang.sizzle.api.LifecyclePhase;
import net.dodogang.sizzle.client.SizzleClient;
import net.dodogang.sizzle.common.Sizzle;
import net.dodogang.sizzle.plugin.PluginManager;
import net.dodogang.sizzle.server.SizzleServer;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod(SizzleInfo.ID)
public class SizzleMod {
    private static final Logger LOGGER = LogManager.getLogger("Sizzle Bootstrap");

    public static final Sizzle INSTANCE = DistExecutor.safeRunForDist(
        () -> SizzleClient::new,
        () -> SizzleServer::new
    );

    private static LifecyclePhase lifecyclePhase = LifecyclePhase.UNLOADED;

    public SizzleMod() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::loadComplete);

        PluginManager.loadPlugins();

        construct();
        LOGGER.info("Sizzle was initialized:");
        LOGGER.info("- Dist:    {}", FMLEnvironment.dist);
        LOGGER.info("- Version: {}", SizzleInfo.VERSION);
    }

    private void construct() {
        lifecyclePhase = LifecyclePhase.CONSTRUCTING;
        INSTANCE.construct();
        PluginManager.getLifecycleListeners().forEach(listener -> listener.sizzleConstruct(INSTANCE));
    }

    private void setup(FMLCommonSetupEvent event) {
        lifecyclePhase = LifecyclePhase.SETTING_UP;
        INSTANCE.setup();
        PluginManager.getLifecycleListeners().forEach(listener -> listener.sizzleSetup(INSTANCE));
    }

    private void loadComplete(FMLLoadCompleteEvent event) {
        lifecyclePhase = LifecyclePhase.COMPLETING;
        INSTANCE.complete();
        PluginManager.getLifecycleListeners().forEach(listener -> listener.sizzleLoaded(INSTANCE));
        lifecyclePhase = LifecyclePhase.LOADED;
    }

    public static LifecyclePhase getLifecyclePhase() {
        return lifecyclePhase;
    }
}
