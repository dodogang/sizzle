package net.dodogang.sizzle.common.handler;

import net.dodogang.sizzle.common.block.SzBlocks;
import net.dodogang.sizzle.common.item.SzItems;
import net.dodogang.sizzle.util.IRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class RegistryHandler {
    @SubscribeEvent
    public void onRegisterBlocks(RegistryEvent.Register<Block> event) {
        SzBlocks.registerBlocks(IRegistry.forge(event.getRegistry()));
    }

    @SubscribeEvent
    public void onRegisterItems(RegistryEvent.Register<Item> event) {
        SzBlocks.registerItems(IRegistry.forge(event.getRegistry()));
        SzItems.registerItems(IRegistry.forge(event.getRegistry()));
    }
}
