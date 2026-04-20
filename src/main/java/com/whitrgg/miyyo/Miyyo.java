package com.whitrgg.miyyo;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Miyyo implements ModInitializer {

    public static final String MOD_ID = "miyyo";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("[Miyyo] Core engine initialized.");
    }
}
