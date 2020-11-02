package net.dodogang.sizzle.test;

import net.dodogang.sizzle.api.ISizzle;
import net.dodogang.sizzle.api.plugin.ISizzleLifecycleListener;
import net.dodogang.sizzle.api.plugin.SizzlePlugin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SizzlePlugin
public class TestSizzlePlugin implements ISizzleLifecycleListener {
    public static final Logger LOGGER = LogManager.getLogger("Sizzle Test Plugin");

    @Override
    public void sizzleConstruct(ISizzle sizzle) {
        LOGGER.info("Sizzle test plugin construct");
    }

    @Override
    public void sizzleSetup(ISizzle sizzle) {
        LOGGER.info("Sizzle test plugin setup");
    }

    @Override
    public void sizzleLoaded(ISizzle sizzle) {
        LOGGER.info("Sizzle test plugin loaded");
    }
}
