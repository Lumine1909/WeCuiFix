package io.github.lumine1909.wecuifix.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.fabric.FabricAdapter;
import com.sk89q.worldedit.fabric.FabricPlayer;
import com.sk89q.worldedit.fabric.FabricWorldEdit;
import com.sk89q.worldedit.fabric.net.handler.WECUIPacketHandler;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import org.enginehub.worldeditcui.protocol.CUIPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(WECUIPacketHandler.class)
public class WeCuiPacketHandlerMixin {

    @WrapMethod(
        method = {"init()V"},
        remap = false
    )
    private static void init(Operation<Void> original) {
        PayloadTypeRegistry.playS2C().register(CUIPacket.TYPE, CUIPacket.CODEC);
        PayloadTypeRegistry.playC2S().register(CUIPacket.TYPE, CUIPacket.CODEC);
        ServerPlayNetworking.registerGlobalReceiver(CUIPacket.TYPE, (payload, context) -> {
            LocalSession session = FabricWorldEdit.inst.getSession(context.player());
            FabricPlayer actor = FabricAdapter.adaptPlayer(context.player());
            session.handleCUIInitializationMessage(getMessage(payload), actor);
        });
    }

    @Unique
    private static String getMessage(CUIPacket packet) {
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
}