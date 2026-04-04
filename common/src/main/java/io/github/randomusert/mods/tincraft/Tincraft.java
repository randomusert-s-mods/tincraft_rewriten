package io.github.randomusert.mods.tincraft;

import io.github.randomusert.mods.tincraft.init.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Tincraft {
    public static final String MOD_ID = "tincraft";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static void init() {
        TCTabs.initTabs();
        TCBlocks.init();
        TCItems.init();

    }
}
