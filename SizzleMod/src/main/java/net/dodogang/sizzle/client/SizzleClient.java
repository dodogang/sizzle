package net.dodogang.sizzle.client;

import net.dodogang.sizzle.api.ISizzleClient;
import net.dodogang.sizzle.common.Sizzle;
import net.dodogang.sizzle.common.block.SzBlocks;
import net.dodogang.sizzle.common.item.SzItems;

public class SizzleClient extends Sizzle implements ISizzleClient {
    @Override
    public void setup() {
        super.setup();
        SzBlocks.setupClient();
        SzItems.setupClient();
    }
}
