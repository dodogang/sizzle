package net.dodogang.sizzle.api;

import net.minecraftforge.eventbus.api.BusBuilder;
import net.minecraftforge.eventbus.api.IEventBus;

import java.lang.reflect.Field;

/**
 * Class that uses reflection to delegate API methods to the mod itself, when available.
 */
final class APIDelegate {
    static final IEventBus EVENT_BUS = BusBuilder.builder().startShutdown().build();
    static ISizzle instance;
    private static boolean found;

    private APIDelegate() {
    }

    static ISizzle get() {
        if (!found) {
            try {
                Class<?> cls = Class.forName("net.dodogang.sizzle.Bootstrap");
                Field field = cls.getField("INSTANCE");
                instance = (ISizzle) field.get(null);
            } catch (Exception ignored) {
            }
            found = true;
        }
        return instance;
    }

    static LifecyclePhase getLifecyclePhase() {
        try {
            Class<?> cls = Class.forName("net.dodogang.sizzle.Bootstrap");
            Field field = cls.getDeclaredField("lifecyclePhase");
            field.setAccessible(true);
            return (LifecyclePhase) field.get(null);
        } catch (Exception ignored) {
            return LifecyclePhase.UNLOADED;
        }
    }
}
