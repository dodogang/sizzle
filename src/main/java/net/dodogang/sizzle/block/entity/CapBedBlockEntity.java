package net.dodogang.sizzle.block.entity;

import net.dodogang.sizzle.init.SizzleBlockEntities;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;

public class CapBedBlockEntity extends BlockEntity {
    private String id;

    public CapBedBlockEntity(String id) {
        super(SizzleBlockEntities.CAP_BED);
        this.id = id;
    }

    public CapBedBlockEntity() {
        super(SizzleBlockEntities.CAP_BED);
    }

    @Override
    public BlockEntityUpdateS2CPacket toUpdatePacket() {
        return new BlockEntityUpdateS2CPacket(this.pos, 11, this.toInitialChunkDataTag());
    }

    public String getId() {
        return id;
    }
}
