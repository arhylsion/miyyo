package com.whitrgg.miyyo.client.logic;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;

public class AutomationCore {
    private static String currentStatus = "Idle";
    private static int waitTimer = 0;
    private static int castGracePeriod = 0;

    public static void register() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null || client.world == null) return;

            if (!client.player.getMainHandStack().isOf(Items.FISHING_ROD)) {
                currentStatus = "Need Fishing Rod";
                castGracePeriod = 0;
                return;
            }

            FishingBobberEntity bobber = client.player.fishHook;

            if (bobber == null) {
                if (waitTimer > 0) {
                    waitTimer--;
                    currentStatus = "Cooldown...";
                } else {
                    currentStatus = "Casting...";
                    useItem(client);
                    castGracePeriod = 30; 
                    waitTimer = 10; 
                }
                return;
            }

            if (castGracePeriod > 0) {
                castGracePeriod--;
                currentStatus = "Bobber flying...";
                return;
            }

            if (bobber.isTouchingWater()) {
                double velY = bobber.getVelocity().y;

                if (velY < -0.09) {
                    currentStatus = "CATCH!";
                    useItem(client);
                    waitTimer = 40; 
                    castGracePeriod = 0;
                } else {
                    currentStatus = "Waiting for fish...";
                }
            } else {
                currentStatus = "Waiting for landing...";
            }
        });
    }

    private static void useItem(MinecraftClient client) {
        if (client.interactionManager != null) {
            client.interactionManager.interactItem(client.player, Hand.MAIN_HAND);
        }
    }

    public static String getStatus() {
        return currentStatus;
    }
}