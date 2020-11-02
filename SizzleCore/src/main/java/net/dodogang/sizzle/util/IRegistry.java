package net.dodogang.sizzle.util;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

import java.util.Objects;

@FunctionalInterface
public interface IRegistry<T extends IForgeRegistryEntry<T>> {
    void register(T t);

    default void register(String id, T t) {
        t.setRegistryName(new ResourceLocation(id));
        register(t);
    }

    default void registerAll(T... ts) {
        for (T t : ts) register(t);
    }

    static <T extends IForgeRegistryEntry<T>> IRegistry<T> forge(IForgeRegistry<T> reg) {
        return reg::register;
    }

    static <T extends IForgeRegistryEntry<T>> IRegistry<T> vanilla(Registry<T> reg) {
        return t -> Registry.register(reg, Objects.requireNonNull(t.getRegistryName()), t);
    }
}
