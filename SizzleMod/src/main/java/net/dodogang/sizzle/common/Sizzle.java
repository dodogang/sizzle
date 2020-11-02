package net.dodogang.sizzle.common;

import net.dodogang.sizzle.SizzleCore;
import net.dodogang.sizzle.SizzleInfo;
import net.dodogang.sizzle.common.handler.RegistryHandler;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public abstract class Sizzle extends SizzleCore {
    public void construct() {
        registerEventListeners();
    }

    public void setup() {
    }

    public void complete() {

    }

    protected void registerEventListeners() {
        FMLJavaModLoadingContext.get().getModEventBus().register(new RegistryHandler());
    }

    /**
     * Create a {@link ResourceLocation} with {@link SizzleInfo#ID sizzle} as default namespace.
     * <ul>
     * <li>{@code "minecraft:path"} will yield {@code minecraft:path}</li>
     * <li>{@code "sizzle:path"} will yield {@code sizzle:path}</li>
     * <li>{@code "path"} will yield {@code sizzle:path} (unlike the {@link ResourceLocation#ResourceLocation(String)
     * ResourceLocation} constructor which will yield {@code minecraft:path})</li>
     * </ul>
     *
     * @param path The resource path.
     * @return The created {@link ResourceLocation} instance.
     */
    public static ResourceLocation resLoc(String path) {
        int colon = path.indexOf(':');
        if (colon >= 0) {
            return new ResourceLocation(path.substring(0, colon), path.substring(colon + 1));
        }
        return new ResourceLocation(SizzleInfo.ID, path);
    }

    /**
     * Create a stringified {@link ResourceLocation} with {@link SizzleInfo#ID sizzle} as default namespace. See {@link
     * #resLoc}.
     *
     * @param path The resource path.
     * @return The created resource id.
     */
    public static String resStr(String path) {
        if (path.indexOf(':') >= 0) {
            return path;
        }
        return SizzleInfo.ID + ":" + path;
    }
}
