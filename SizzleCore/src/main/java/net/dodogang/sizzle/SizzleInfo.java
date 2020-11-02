package net.dodogang.sizzle;

import net.dodogang.sizzle.api.ISizzleInfo;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.loading.FMLEnvironment;

/**
 * Class that holds constants about Sizzle, which are usually replaced by Gradle. Most constants are used in commands to
 * display the version message.
 */
public final class SizzleInfo {
    public static final ISizzleInfo INSTANCE = new Instance();

    /**
     * The mod ID of Sizzle, which is {@code ndebris}.
     */
    @DynamicConstant("mod_id")
    public static final String ID = "ndebris";

    /**
     * The version of Sizzle, injected by gradle and otherwise "IDE".
     */
    @DynamicConstant("version")
    public static final String VERSION = "IDE";

    /**
     * The version name of Sizzle, injected by gradle and otherwise "The IDE Version".
     */
    @DynamicConstant("version_name")
    public static final String VERSION_NAME = "The IDE Version";

    /**
     * SHA1 of Sizzle. Unused, but prereserved for later use. Injected by gradle and otherwise {@code
     * NO:FI:NG:ER:PR:IN:TA:VA:IL:AB:LE}.
     */
    @DynamicConstant("sha1")
    public static final String SHA1 = "NO:FI:NG:ER:PR:IN:TA:VA:IL:AB:LE";

    /**
     * Boolean indicating we are running from the IDE. Unused, but preserved for later use. Injected by gradle and
     * otherwise true.
     */
    public static final boolean IDE = Boolean.parseBoolean(System.getProperty("sizzle.env.ide", "false"));

    /**
     * Boolean indicating the current JAR is signed, if there is any jar, and otherwise false. Unused, but preserved for
     * later use. Injected by gradle and otherwise false.
     */
    @DynamicConstant("signed")
    public static final boolean SIGNED = false;

    private SizzleInfo() {
    }

    private static class Instance implements ISizzleInfo {
        @Override
        public String version() {
            return VERSION;
        }

        @Override
        public String versionName() {
            return VERSION_NAME;
        }

        @Override
        public boolean isClient() {
            return FMLEnvironment.dist == Dist.CLIENT;
        }

        @Override
        public boolean isDedicatedServer() {
            return FMLEnvironment.dist == Dist.DEDICATED_SERVER;
        }

        @Override
        public boolean isDevVersion() {
            return IDE;
        }
    }
}
