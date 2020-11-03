package net.dodogang.sizzle.common.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.material.PushReaction;

public final class SzMaterials {
    public static final Material GHAST_JELLY = new Builder(MaterialColor.WHITE_TERRACOTTA)
                                                   .allowMovement()
                                                   .notOpaque()
                                                   .notSolid()
                                                   .build();

    private SzMaterials() {
    }


    public static class Builder {
        private PushReaction pushReaction = PushReaction.NORMAL;
        private boolean blocksMovement = true;
        private boolean canBurn;
        private boolean isLiquid;
        private boolean isReplaceable;
        private boolean isSolid = true;
        private final MaterialColor color;
        private boolean isOpaque = true;

        public Builder(MaterialColor color) {
            this.color = color;
        }

        public Builder liquid() {
            this.isLiquid = true;
            return this;
        }

        public Builder notSolid() {
            this.isSolid = false;
            return this;
        }

        public Builder allowMovement() {
            this.blocksMovement = false;
            return this;
        }

        public Builder notOpaque() {
            this.isOpaque = false;
            return this;
        }

        public Builder flammable() {
            this.canBurn = true;
            return this;
        }

        public Builder replaceable() {
            this.isReplaceable = true;
            return this;
        }

        public Builder pushDestroys() {
            this.pushReaction = PushReaction.DESTROY;
            return this;
        }

        public Builder pushBlocks() {
            this.pushReaction = PushReaction.BLOCK;
            return this;
        }

        public Material build() {
            return new Material(this.color, this.isLiquid, this.isSolid, this.blocksMovement, this.isOpaque, this.canBurn, this.isReplaceable, this.pushReaction);
        }
    }
}
