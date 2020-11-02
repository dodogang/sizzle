package net.dodogang.sizzle.api.event;


import net.dodogang.sizzle.api.Side;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Registers an event handler class to the Sizzle event bus.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface SizzleEventBusSubscriber {
    /**
     * The side to register the event handlers on. The returned side must match the runtime dist in order to load and
     * register the annotated class. Default is {@link Side#COMMON}.
     */
    Side value() default Side.COMMON;
}
