package com.whitrgg.miyyo.client;

import net.fabricmc.api.ClientModInitializer;
import com.whitrgg.miyyo.Miyyo;
import com.whitrgg.miyyo.client.feature.TestModule;
import com.whitrgg.miyyo.client.ui.MiyyoHud;
import com.whitrgg.miyyo.client.render.BlockVisualizer;
import com.whitrgg.miyyo.client.logic.AutomationCore;

public class MiyyoClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        Miyyo.LOGGER.info("[Miyyo] Client module initialized.");

        TestModule.register();
        MiyyoHud.register();
        BlockVisualizer.register();
        AutomationCore.register();
    }
}
