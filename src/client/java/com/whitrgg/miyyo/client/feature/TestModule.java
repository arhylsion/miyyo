package com.whitrgg.miyyo.client.feature;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class TestModule {

    private static KeyBinding testKeyBinding;

    public static void register() {
        testKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.miyyo.test",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_O,
                "category.miyyo.general"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (testKeyBinding.wasPressed()) {
                if (client.player != null) {
                    client.player.sendMessage(Text.literal("§a[Miyyo] Split Source system is running perfectly!"), false);
                }
            }
        });
    }
}
