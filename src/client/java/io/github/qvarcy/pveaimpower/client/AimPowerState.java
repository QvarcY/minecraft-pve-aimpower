package io.github.qvarcy.pveaimpower.client;

public final class AimPowerState {
    private boolean enabled;

    public boolean isEnabled() {
        return enabled;
    }

    public boolean toggleEnabled() {
        enabled = !enabled;
        return enabled;
    }
}
