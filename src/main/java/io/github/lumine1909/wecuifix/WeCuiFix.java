package io.github.lumine1909.wecuifix;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WeCuiFix implements ModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("wecuifix");

    @Override
    public void onInitialize() {
        LOGGER.info("Initializing WeCuiFix...");
    }
}