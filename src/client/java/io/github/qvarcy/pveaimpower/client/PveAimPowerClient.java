package io.github.qvarcy.pveaimpower.client;

import java.util.List;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.LivingEntity;

public final class PveAimPowerClient implements ClientModInitializer {
    public static final String MOD_ID = "pve_aimpower";

    private final AimPowerState state = new AimPowerState();
    private final TargetLock targetLock = new TargetLock();

    private final KeyMapping.Category category = KeyMapping.Category.register(
        Identifier.fromNamespaceAndPath(MOD_ID, "controls")
    );

    private final KeyMapping toggleKey = KeyMappingHelper.registerKeyMapping(
        new KeyMapping(
            "key.pve_aimpower.toggle",
            InputConstants.Type.KEYSYM,
            InputConstants.KEY_G,
            category
        )
    );

    private final KeyMapping targetLockKey = KeyMappingHelper.registerKeyMapping(
        new KeyMapping(
            "key.pve_aimpower.target_lock",
            InputConstants.Type.KEYSYM,
            InputConstants.KEY_R,
            category
        )
    );

    @Override
    public void onInitializeClient() {
        ClientTickEvents.START_CLIENT_TICK.register(this::onClientTick);
    }

    private void onClientTick(Minecraft client) {
        handleToggle(client);
        handleTargetLock(client);

        if (
            !state.isEnabled()
            || client.player == null
            || client.level == null
            || client.gui.screen() != null
        ) {
            return;
        }

        if (
            targetLock.hasTarget()
            && !targetLock.isStillValid(client)
        ) {
            targetLock.clear();

            client.player.sendSystemMessage(
                Component.literal(
                    "PvE AimPower: target lost"
                )
            );
        }
    }

    private void handleToggle(Minecraft client) {
        while (toggleKey.consumeClick()) {
            boolean enabled = state.toggleEnabled();

            if (!enabled) {
                targetLock.clear();
            }

            if (client.player != null) {
                client.player.sendSystemMessage(
                    Component.literal(
                        "PvE AimPower: "
                            + (enabled ? "ON" : "OFF")
                    )
                );
            }
        }
    }

    private void handleTargetLock(Minecraft client) {
        while (targetLockKey.consumeClick()) {
            if (
                client.player == null
                || client.level == null
            ) {
                continue;
            }

            if (!state.isEnabled()) {
                client.player.sendSystemMessage(
                    Component.literal(
                        "PvE AimPower: OFF - press G first"
                    )
                );

                continue;
            }

            if (targetLock.hasTarget()) {
                String name = targetLock
                    .getTarget()
                    .getName()
                    .getString();

                targetLock.clear();

                client.player.sendSystemMessage(
                    Component.literal(
                        "PvE AimPower: released " + name
                    )
                );

                continue;
            }

            List<LivingEntity> candidates =
                TargetScanner.scan(client);

            LivingEntity target =
                TargetSelector.selectBest(
                    client.player,
                    candidates
                );

            if (target == null) {
                client.player.sendSystemMessage(
                    Component.literal(
                        "PvE AimPower: no hostile target"
                    )
                );

                continue;
            }

            targetLock.lock(target);

            client.player.sendSystemMessage(
                Component.literal(
                    "PvE AimPower: locked "
                        + target.getName().getString()
                        + " | "
                        + String.format(
                            "%.1f m",
                            client.player.distanceTo(target)
                        )
                )
            );
        }
    }
}
