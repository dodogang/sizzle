package net.dodogang.sizzle.api.event;

import net.dodogang.sizzle.api.ISizzle;
import net.dodogang.sizzle.api.LifecyclePhase;
import net.minecraftforge.eventbus.api.Event;

public class SizzleLifecycleEvent extends Event {
    private final LifecyclePhase phase;
    private final ISizzle naturesDebris;

    public SizzleLifecycleEvent(LifecyclePhase phase, ISizzle naturesDebris) {
        this.phase = phase;
        this.naturesDebris = naturesDebris;
    }

    public LifecyclePhase getLifecyclePhase() {
        return phase;
    }

    public ISizzle getNaturesDebris() {
        return naturesDebris;
    }
}
