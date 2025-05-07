package io.github.lumine1909.wecuifix;

import net.fabricmc.api.ModInitializer;
import org.enginehub.worldeditcui.protocol.CUIPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class WeCuiFix implements ModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("wecuifix");

    public static String encode(CUIPacket packet) {
        final StringBuilder builder = new StringBuilder();
        if (packet.multi()) {
            builder.append('+');
        }
        builder.append(packet.eventType());
        for (final String arg : packet.args()) {
            builder.append('|');
            builder.append(arg);
        }
        return builder.toString();
    }

    public static CUIPacket decode(String original) {
        String[] split = original.split("\\|", -1);
        boolean multi = split[0].startsWith("+");
        String type = split[0].substring(multi ? 1 : 0);
        List<String> args = split.length > 1 ? List.of(Arrays.copyOfRange(split, 1, split.length)) : List.of();
        return new CUIPacket(multi, type, args);
    }

    @Override
    public void onInitialize() {
        LOGGER.info("Initializing WeCuiFix...");
    }
}