package io.github.qvarcy.pveaimpower.client;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;

public final class TargetScanner {
    public static final double MAX_RANGE = 12.0;

    private TargetScanner() {
    }

    public static List<LivingEntity> scan(Minecraft client) {
        List<LivingEntity> targets = new ArrayList<>();

        if (client.player == null || client.level == null) {
            return targets;
        }

        LocalPlayer player = client.player;

        for (Entity entity : client.level.entitiesForRendering()) {
            if (!(entity instanceof LivingEntity living)) {
                continue;
            }

            if (!isValidTarget(player, living)) {
                continue;
            }

            targets.add(living);
        }

        return targets;
    }

    public static boolean isValidTarget(
        LocalPlayer player,
        LivingEntity target
    ) {
        if (target == player) {
            return false;
        }

        // players never enter the PvE target pipeline
        if (target instanceof Player) {
            return false;
        }

        if (!(target instanceof Enemy)) {
            return false;
        }

        if (!target.isAlive() || target.isRemoved()) {
            return false;
        }

        if (player.distanceToSqr(target) > MAX_RANGE * MAX_RANGE) {
            return false;
        }

        return player.hasLineOfSight(target);
    }
}
