package io.github.qvarcy.pveaimpower.client;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.LivingEntity;

public final class TargetLock {
    private LivingEntity target;

    public LivingEntity getTarget() {
        return target;
    }

    public boolean hasTarget() {
        return target != null;
    }

    public void lock(LivingEntity target) {
        this.target = target;
    }

    public void clear() {
        target = null;
    }

    public boolean isStillValid(Minecraft client) {
        if (
            target == null
            || client.player == null
            || client.level == null
        ) {
            return false;
        }

        return TargetScanner.isValidTarget(
            client.player,
            target
        );
    }
}
