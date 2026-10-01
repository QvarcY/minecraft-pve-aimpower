package io.github.qvarcy.pveaimpower.client;

import net.minecraft.client.player.LocalPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public final class AimController {
    private static final float MAX_YAW_STEP = 4.0F;
    private static final float MAX_PITCH_STEP = 3.0F;

    private AimController() {
    }

    public static void update(
        LocalPlayer player,
        LivingEntity target
    ) {
        if (target == null) {
            return;
        }

        // second wall even if the scanner ever gets confused
        if (target instanceof Player) {
            return;
        }

        if (!target.isAlive() || target.isRemoved()) {
            return;
        }

        Vec3 eye = player.getEyePosition();

        Vec3 aimPoint = target.position().add(
            0.0,
            target.getBbHeight() * 0.65,
            0.0
        );

        double dx = aimPoint.x - eye.x;
        double dy = aimPoint.y - eye.y;
        double dz = aimPoint.z - eye.z;

        double horizontal = Math.sqrt(
            dx * dx + dz * dz
        );

        if (horizontal < 1.0E-6) {
            return;
        }

        float targetYaw = (float) Math.toDegrees(
            Math.atan2(dz, dx)
        ) - 90.0F;

        float targetPitch = (float) -Math.toDegrees(
            Math.atan2(dy, horizontal)
        );

        float yawDelta = Mth.wrapDegrees(
            targetYaw - player.getYRot()
        );

        float pitchDelta = Mth.wrapDegrees(
            targetPitch - player.getXRot()
        );

        float yawStep = Mth.clamp(
            yawDelta,
            -MAX_YAW_STEP,
            MAX_YAW_STEP
        );

        float pitchStep = Mth.clamp(
            pitchDelta,
            -MAX_PITCH_STEP,
            MAX_PITCH_STEP
        );

        player.setYRot(
            player.getYRot() + yawStep
        );

        player.setXRot(
            Mth.clamp(
                player.getXRot() + pitchStep,
                -90.0F,
                90.0F
            )
        );
    }
}
