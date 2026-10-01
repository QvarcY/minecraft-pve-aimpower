package io.github.qvarcy.pveaimpower.client;

import java.util.List;

import net.minecraft.client.player.LocalPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public final class TargetSelector {
    public static final double MAX_TARGET_ANGLE = 45.0;

    private TargetSelector() {
    }

    public static LivingEntity selectBest(
        LocalPlayer player,
        List<LivingEntity> candidates
    ) {
        Vec3 lookDirection = player.getLookAngle().normalize();
        Vec3 eyePosition = player.getEyePosition();

        LivingEntity bestTarget = null;
        double bestAngle = Double.MAX_VALUE;
        double bestDistance = Double.MAX_VALUE;

        for (LivingEntity candidate : candidates) {
            Vec3 targetDirection = candidate
                .getEyePosition()
                .subtract(eyePosition);

            if (targetDirection.lengthSqr() < 1.0E-8) {
                continue;
            }

            targetDirection = targetDirection.normalize();

            double dot = Mth.clamp(
                lookDirection.dot(targetDirection),
                -1.0,
                1.0
            );

            double angle = Math.toDegrees(Math.acos(dot));

            if (angle > MAX_TARGET_ANGLE) {
                continue;
            }

            double distance = player.distanceToSqr(candidate);

            if (
                angle < bestAngle
                || (
                    Math.abs(angle - bestAngle) < 0.001
                    && distance < bestDistance
                )
            ) {
                bestTarget = candidate;
                bestAngle = angle;
                bestDistance = distance;
            }
        }

        return bestTarget;
    }
}
