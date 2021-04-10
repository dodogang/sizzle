package net.dodogang.sizzle;

import com.google.common.collect.ImmutableList;
import me.andante.chord.item.item_group.AbstractTabbedItemGroup;
import me.andante.chord.item.item_group.ItemGroupTab;
import net.dodogang.sizzle.init.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Sizzle implements ModInitializer {
    public static final String MOD_ID = "sizzle";
    public static final String MOD_NAME = "Sizzle";

    public static Logger LOGGER = LogManager.getLogger(MOD_ID);

    public static final ItemGroup ITEM_GROUP = new AbstractTabbedItemGroup(MOD_ID) {
        @Override
        protected List<ItemGroupTab> initTabs() {
            return ImmutableList.of(
                createTab(SizzleBlocks.BROWN_CAP, "building_blocks"),
                createTab(SizzleBlocks.RED_CAP, "decoration_blocks"),
                createTab(SizzleItems.WITHER_BONE_MEAL, "miscellaneous")
            );
        }

        @Override
        public ItemStack createIcon() {
            return new ItemStack(SizzleBlocks.WITHER_BONE_BLOCK);
        }
    };

    @Override
    public void onInitialize() {
        log("Initializing");

        new SizzleParticles();

        new SizzleBlocks();
        new SizzleItems();

        for (Identifier identifier : new Identifier[]{ EntityType.WITHER_SKELETON.getLootTableId() }) {
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
        log(Level.INFO, message);
    }
}
