package net.dodogang.sizzle;

import net.dodogang.sizzle.init.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
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

        new SizzleParticles();
        new SizzleBlockEntities();

        new SizzleItems();
        new SizzleBlocks();

        for (Identifier identifier : new Identifier[]{ new Identifier("entities/wither_skeleton") }) {
            Identifier VANILLA_TABLE = new Identifier(identifier.toString());
            Identifier ADDITION_TABLE = new Identifier(Sizzle.MOD_ID, "additions/" + identifier.getNamespace() + "/" + identifier.getPath());

            LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, supplier, setter) -> {
                if (VANILLA_TABLE.equals(id)) supplier.copyFrom(lootManager.getTable(ADDITION_TABLE));
            });
        }

        log("Initialized");
    }

    public static void log(Level level, String message){
        LOGGER.log(level, "[" + MOD_NAME + "] " + message);
    }
    public static void log(String message){
        LOGGER.log(Level.INFO, "[" + MOD_NAME + "] " + message);
    }
}
