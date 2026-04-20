package com.whitrgg.miyyo.client.ui;

import com.whitrgg.miyyo.client.logic.AutomationCore;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;

public class MiyyoHud {

    public static void register() {
        HudRenderCallback.EVENT.register((drawContext, tickCounter) -> {
            MinecraftClient client = MinecraftClient.getInstance();

            if (client.player == null || client.options.hudHidden) {
                return;
            }

            renderMiyyoStatus(drawContext, client.textRenderer);
        });
    }

    private static void renderMiyyoStatus(DrawContext context, TextRenderer textRenderer) {
        String status = AutomationCore.getStatus();
        String displayText = "§b[Miyyo] §fStatus: §a" + status;

        int x = 10;
        int y = 10;

        context.drawTextWithShadow(textRenderer, displayText, x, y, 0xFFFFFF);
    }
}