package net.dodogang.sizzle.data.loottables;

import net.dodogang.sizzle.common.block.SzBlocks;
import net.minecraft.block.Block;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.loot.ConstantRange;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class SizzleBlockLootTables extends BlockLootTables {

    protected static LootTable.Builder droppingNothing() {
        return LootTable.builder().addLootPool(LootPool.builder().rolls(ConstantRange.of(0)));
    }

    @Override
    protected void addTables() {
        registerDropSelfLootTable(SzBlocks.BLAZE_ROD_BLOCK);
        registerDropSelfLootTable(SzBlocks.PUMICE);
        registerDropSelfLootTable(SzBlocks.POLISHED_PUMICE);
        registerLootTable(SzBlocks.PUMICE_SLAB, BlockLootTables::droppingSlab);
        registerLootTable(SzBlocks.POLISHED_PUMICE_SLAB, BlockLootTables::droppingSlab);
        registerDropSelfLootTable(SzBlocks.PUMICE_STAIRS);
        registerDropSelfLootTable(SzBlocks.POLISHED_PUMICE_STAIRS);
        registerDropSelfLootTable(SzBlocks.PUMICE_WALL);
        registerDropSelfLootTable(SzBlocks.POLISHED_PUMICE_WALL);
        registerDropSelfLootTable(SzBlocks.GHAST_JELLY_BLOCK);
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return StreamSupport.stream(ForgeRegistries.BLOCKS.spliterator(), false)
                            .filter(block -> Objects.requireNonNull(block.getRegistryName()).getNamespace().equals("sizzle"))
                            .collect(Collectors.toList());
    }
}
