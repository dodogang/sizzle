package net.dodogang.sizzle.api.plugin;


import net.dodogang.sizzle.api.Side;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates a plugin for Sizzle. All classes annotated with {@code @NaturesDebrisPlugin} are recognized by the Sizzle
 * mod and loaded right before construction time.
 * <p>
 * A distribution side may be given as optional {@code value} argument to make your plugin only load on a specific
 * distribution:
 * <ul>
 * <li>{@link Side#COMMON COMMON}: The plugin always loads (default)</li>
 * <li>{@link Side#CLIENT CLIENT}: The plugin only loads on the client distribution</li>
 * <li>{@link Side#SERVER SERVER}: The plugin only loads on the dedicated server distribution</li>
 * </ul>
 * <p>
 * Example:
 * <pre>
 * &#64;NaturesDebrisPlugin( Side.CLIENT )
 * public class MyClientPlugin implements ILifecycleListener {
 *     &#64;Override
 *     public void ndebrisSetup( INaturesDebris nd ) {
 *         System.out.println( "Plugin loaded" );
 *     }
 * }
 * </pre>
 * Once Sizzle loads this plugin, it will print "{@code Plugin loaded}" once Sizzle finished setup-time
 * loading. For more info, check the wiki on GitHub.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface SizzlePlugin {
    Side value() default Side.COMMON;

}
