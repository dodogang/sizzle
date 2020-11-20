package net.dodogang.sizzle;

import net.dodogang.sizzle.init.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Sizzle implements ModInitializer {
    public static Logger LOGGER = LogManager.getLogger();

    public static final String MOD_ID = "sizzle";
    public static final String MOD_NAME = "Sizzle";

    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
        new Identifier(MOD_ID, "item_group"),
        () -> new ItemStack(SizzleBlocks.BLAZE_ROD_BLOCK)
    );

    @Override
    public void onInitialize() {
        log("Initializing");

        new SizzleBlocks();

        log("Initialized");
    }

    public static void log(Level level, String message){
        LOGGER.log(level, "[" + MOD_NAME + "] " + message);
    }
    public static void log(String message){
        LOGGER.log(Level.INFO, "[" + MOD_NAME + "] " + message);
    }
}
