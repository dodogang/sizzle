package net.dodogang.sizzle.data.models.stategen;

import com.google.gson.JsonElement;
import net.dodogang.sizzle.data.models.modelgen.IModelGen;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;

import java.util.function.BiConsumer;

public interface IBlockStateGen {
    JsonElement makeJson(ResourceLocation id, Block block);
    void getModels(BiConsumer<String, IModelGen> consumer);
}
