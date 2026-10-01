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

        if (particleTick >= 2) {
            particleTick = 0;
            spawnMarkerParticles(client, target);
        }

        if (overlayTick >= 4) {
            overlayTick = 0;

            client.gui.hud.setOverlayMessage(
                Component.literal(
                    "LOCKED: "
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
        double chestY = target.getY()
            + target.getBbHeight() * 0.65;

        double headY = target.getY()
            + target.getBbHeight()
            + 0.25;

        double time = target.tickCount * 18.0;

        for (int i = 0; i < 10; i++) {
            double angle = Math.toRadians(
                time + (i * 36.0)
            );

            double chestX = target.getX()
                + Math.cos(angle) * 0.38;

            double chestZ = target.getZ()
                + Math.sin(angle) * 0.38;

            client.level.addParticle(
                ParticleTypes.CRIT,
                chestX,
                chestY,
                chestZ,
                0.0,
                0.015,
                0.0
            );
        }

        for (int i = 0; i < 12; i++) {
            double angle = Math.toRadians(
                -time + (i * 30.0)
            );

            double headX = target.getX()
                + Math.cos(angle) * 0.55;

            double headZ = target.getZ()
                + Math.sin(angle) * 0.55;

            client.level.addParticle(
                ParticleTypes.FLAME,
                headX,
                headY,
                headZ,
                0.0,
                0.01,
                0.0
            );
        }
    }
}
