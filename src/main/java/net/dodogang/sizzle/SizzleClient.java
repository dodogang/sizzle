package net.dodogang.sizzle;

import net.dodogang.sizzle.init.SizzleBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class SizzleClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap INSTANCE = BlockRenderLayerMap.INSTANCE;
        // INSTANCE.putBlocks(RenderLayer.getCutout(), SzBlocks.FUNGI.DOOR, SzBlocks.FUNGI.DOOR);
    }
}
