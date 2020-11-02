package net.dodogang.sizzle.api;

public interface ISizzleInfo {
    String version();
    String versionName();
    boolean isClient();
    boolean isDedicatedServer();
    boolean isDevVersion();
}
