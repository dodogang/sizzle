package net.dodogang.sizzle;

import net.dodogang.sizzle.init.SizzleBlocks;
import net.dodogang.sizzle.init.SizzleItems;
import net.dodogang.sizzle.init.SizzleParticles;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Sizzle implements ModInitializer {
    public static final String MOD_ID = "sizzle";
    public static final String MOD_NAME = "Sizzle";

    public static Logger LOGGER = LogManager.getLogger(MOD_ID);

    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
        new Identifier(MOD_ID, "item_group"),
        () -> new ItemStack(SizzleBlocks.WITHER_BONE_BLOCK)
    );

    @Override
    public void onInitialize() {
        log("Initializing");

        new SizzleParticles();

        new SizzleBlocks();
        new SizzleItems();

        for (Identifier identifier : new Identifier[]{ new Identifier("entities/" + Registry.ENTITY_TYPE.getId(EntityType.WITHER_SKELETON).getPath()) }) {
            Identifier ADDITION_TABLE = new Identifier(Sizzle.MOD_ID, "additions/" + identifier.getNamespace() + "/" + identifier.getPath());
            LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, supplier, setter) -> {
                if (identifier.equals(id)) supplier.copyFrom(lootManager.getTable(ADDITION_TABLE));
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
