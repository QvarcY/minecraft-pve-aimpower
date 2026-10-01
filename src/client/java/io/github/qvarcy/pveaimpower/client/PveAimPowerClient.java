package io.github.qvarcy.pveaimpower.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

public final class PveAimPowerClient implements ClientModInitializer {
    public static final String MOD_ID = "pve_aimpower";

    private final AimPowerState state = new AimPowerState();

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
        while (toggleKey.consumeClick()) {
            boolean enabled = state.toggleEnabled();

            if (client.player != null) {
                client.player.sendSystemMessage(Component.literal(
                    "PvE AimPower: " + (enabled ? "ON" : "OFF")
                ));
            }
        }

        while (targetLockKey.consumeClick()) {
            if (client.player == null) {
                continue;
            }

            if (!state.isEnabled()) {
                client.player.sendSystemMessage(Component.literal(
                    "PvE AimPower: OFF - press G first"
                ));
                continue;
            }

            client.player.sendSystemMessage(Component.literal(
                "PvE AimPower: target lock input detected"
            ));
        }
    }
}
