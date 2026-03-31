package io.github.randomusert.mods.tincraft;

import io.github.randomusert.mods.tincraft.init.*;

public final class Tincraft {
    public static final String MOD_ID = "tincraft";

    public static void init() {
        TCTabs.initTabs();
        TCBlocks.init();
        TCItems.init();

    }
}
