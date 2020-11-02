package net.dodogang.sizzle.api;

public enum LifecyclePhase {
    UNLOADED,
    CONSTRUCTING,
    SETTING_UP,
    COMPLETING,
    LOADED;

    public static LifecyclePhase get() {
        return APIDelegate.getLifecyclePhase();
    }
}
