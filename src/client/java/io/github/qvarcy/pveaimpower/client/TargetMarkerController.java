package io.github.qvarcy.pveaimpower.client;

import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public final class TargetMarkerController {
    private LivingEntity markedTarget;
    private int particleTick;
    private int overlayTick;

    public void clear() {
        if (markedTarget != null) {
            markedTarget.setGlowingTag(false);
            markedTarget = null;
        }

        particleTick = 0;
        overlayTick = 0;
    }

    public void update(
        Minecraft client,
        LivingEntity target
    ) {
        if (
            client.player == null
            || client.level == null
            || target == null
        ) {
            clear();
            return;
        }

        // third wall just to be extra sure
        if (
            target instanceof Player
            || !target.isAlive()
            || target.isRemoved()
        ) {
            clear();
            return;
        }

        if (
            markedTarget != null
            && markedTarget != target
        ) {
            markedTarget.setGlowingTag(false);
        }

        markedTarget = target;
        markedTarget.setGlowingTag(true);

        particleTick++;
        overlayTick++;

        if (particleTick >= 3) {
            particleTick = 0;
            spawnMarkerParticles(client, target);
        }

        if (overlayTick >= 5) {
            overlayTick = 0;

            client.gui.hud.setOverlayMessage(
                Component.literal(
                    "Target: "
                        + target.getName().getString()
                        + " | "
                        + String.format(
                            "%.1f m",
                            client.player.distanceTo(target)
                        )
                ),
                false
            );
        }
    }

    private void spawnMarkerParticles(
        Minecraft client,
        LivingEntity target
    ) {
        double y = target.getY()
            + target.getBbHeight()
            + 0.25;

        for (int i = 0; i < 6; i++) {
            double angle = Math.toRadians(
                (target.tickCount * 18.0)
                    + (i * 60.0)
            );

            double x = target.getX()
                + Math.cos(angle) * 0.45;

            double z = target.getZ()
                + Math.sin(angle) * 0.45;

            client.level.addParticle(
                ParticleTypes.CRIT,
                x,
                y,
                z,
                0.0,
                0.02,
                0.0
            );
        }
    }
}
