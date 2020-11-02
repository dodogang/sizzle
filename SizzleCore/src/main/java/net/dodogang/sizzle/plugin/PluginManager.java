package net.dodogang.sizzle.plugin;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import net.dodogang.sizzle.api.Side;
import net.dodogang.sizzle.api.event.SizzleEventBusSubscriber;
import net.dodogang.sizzle.api.plugin.ISizzleLifecycleListener;
import net.dodogang.sizzle.api.plugin.SizzlePlugin;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.forgespi.language.ModFileScanData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.Type;

import java.lang.annotation.ElementType;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public final class PluginManager {
    private static final Logger LOGGER = LogManager.getLogger("Sizzle Plugin Manager");

    private static final Set<PluginHolder> PLUGINS = Sets.newHashSet();
    private static final Set<ASMPluginData> PLUGIN_ASM = Sets.newHashSet();
    private static final Set<ASMPluginData> EVENT_SUB_ASM = Sets.newHashSet();
    private static List<ISizzleLifecycleListener> lifecycleListeners;

    private static final List<PluginException> PLUGIN_ERRORS = Lists.newArrayList();

    private static final Type PLUGIN_ANNOTATION = Type.getType(SizzlePlugin.class);
    private static final Type EVENT_SUB_ANNOTATION = Type.getType(SizzleEventBusSubscriber.class);
    private static final Type SIDE_TYPE = Type.getType(Side.class);


    private PluginManager() {
    }

    /**
     * Loads all Sizzle plugins (classes annotated with {@link SizzlePlugin @SizzlePlugin}) and all Sizzle event bus
     * subscribers (classes annotated with {@link SizzleEventBusSubscriber @SizzleEventBusSubscriber}). This uses
     * Forge's {@link ModFileScanData} to find annotated classes.
     */
    public static void loadPlugins() {
        LOGGER.info("Loading plugins...");

        // Search for ASM data
        List<ModFileScanData> scanData = ModList.get().getAllScanData();
        for (ModFileScanData data : scanData) {
            analyze(data);
        }

        // Subscribe all event subscribers
        for (ASMPluginData data : EVENT_SUB_ASM) {
            try {
                if (data.shouldLoad(FMLEnvironment.dist)) {
                    data.registerEventSubscriber();
                }
            } catch (Exception exc) {
                LOGGER.error("Unable to create SizzleEventBusSubscriber: " + exc.getMessage());
            }
        }

        // Load all necessary classes from ASM data
        for (ASMPluginData data : PLUGIN_ASM) {
            try {
                if (data.shouldLoad(FMLEnvironment.dist)) {
                    PLUGINS.add(data.makeHolder());
                }
            } catch (PluginException exc) {
                PLUGIN_ERRORS.add(exc);
            }
        }

        // Clear these as we don't need these memory references anymore (saves some memory)
        PLUGIN_ASM.clear();
        EVENT_SUB_ASM.clear();

        // Instantiates all plugins queued for loading
        for (PluginHolder holder : PLUGINS) {
            try {
                holder.makeInstance();
            } catch (PluginException exc) {
                PLUGIN_ERRORS.add(exc);
            }
        }

        // Find ILifecycleListeners
        lifecycleListeners = ImmutableList.copyOf(getAllOfType(ISizzleLifecycleListener.class));

        // Log debug info
        LOGGER.info("Loaded {} plugins", PLUGINS.size());
        LOGGER.debug("Loaded plugins:");
        for (PluginHolder holder : PLUGINS) {
            LOGGER.debug(" - " + holder.getInstance());
        }

        // Log errors
        if (PLUGIN_ERRORS.size() > 0) {
            LOGGER.error("{} plugins failed loading", PLUGIN_ERRORS.size());

            for (PluginException err : PLUGIN_ERRORS) {
                if (err.getCause() == null) {
                    LOGGER.error(err.getMessage());
                } else {
                    LOGGER.error(err.getMessage());
                    LOGGER.error("Stacktrace: ", err.getCause());
                }
            }
        }
    }

    /**
     * Gets all plugin instances of a specific type, cast to that specific type.
     *
     * @param type The type class
     * @return A list of cast plugin instances.
     */
    public static <T> List<T> getAllOfType(Class<T> type) {
        List<T> list = Lists.newArrayList();

        for (PluginHolder holder : PLUGINS) {
            if (type.isAssignableFrom(holder.getClazz())) {
                list.add(type.cast(holder.getInstance()));
            }
        }

        return list;
    }

    /**
     * Returns a {@link Stream} of plugin instances that implement {@link ISizzleLifecycleListener}.
     */
    public static Stream<ISizzleLifecycleListener> getLifecycleListeners() {
        return lifecycleListeners.stream();
    }

    /**
     * Analyzes the {@link ModFileScanData} for {@link SizzlePlugin @SizzlePlugin} annotations.
     */
    private static void analyze(ModFileScanData data) {
        Set<ModFileScanData.AnnotationData> annotations = data.getAnnotations();

        for (ModFileScanData.AnnotationData annotationData : annotations) {

            if (annotationData.getTargetType() == ElementType.TYPE) {
                if (PLUGIN_ANNOTATION.equals(annotationData.getAnnotationType())) {
                    PLUGIN_ASM.add(new ASMPluginData(
                        annotationData.getClassType(),
                        distFromAnotationValue(annotationData.getAnnotationData().get("side"))
                    ));
                } else if (EVENT_SUB_ANNOTATION.equals(annotationData.getAnnotationType())) {
                    EVENT_SUB_ASM.add(new ASMPluginData(
                        annotationData.getClassType(),
                        distFromAnotationValue(annotationData.getAnnotationData().get("side"))
                    ));
                }
            }

        }
    }

    /**
     * Computes the dist from the ASM value of {@link Side}. Returns null for invalid or common side. Given array should
     * be a String array with two non-null strings.
     *
     * @param value The ASM value of {@link Side}.
     * @return The side's {@link Dist}, or null for a common side.
     */
    private static Dist distFromAnotationValue(Object value) {
        if (!(value instanceof String[])) {
            return null;
        }
        String[] values = (String[]) value;
        if (values.length != 2) return null;
        if (values[0] == null) return null;
        if (values[1] == null) return null;

        Type desc = Type.getType(values[0]);
        String name = values[1];
        if (SIDE_TYPE.equals(desc)) {
            switch (name) {
                case "CLIENT":
                    return Dist.CLIENT;
                case "SERVER":
                    return Dist.DEDICATED_SERVER;
            }
        }
        // Default switch case automatically lead to null return
        return null;
    }
}
