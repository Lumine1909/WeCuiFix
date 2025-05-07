package io.github.lumine1909.wecuifix.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.sk89q.worldedit.fabric.net.handler.WECUIPacketHandler;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import org.enginehub.worldeditcui.protocol.CUIPacket;
import org.spongepowered.asm.mixin.Mixin;

import static io.github.lumine1909.wecuifix.WeCuiFix.encode;

@Mixin(targets = "org.enginehub.worldeditcui.fabric.CUINetworking")
public class CuiNetworkingMixin {

    @WrapMethod(
        method = {"send"},
        remap = false
    )
    private static void send(CUIPacket pkt, Operation<Void> original) {
        WECUIPacketHandler.CuiPacket packet = new WECUIPacketHandler.CuiPacket(encode(pkt));
        ClientPlayNetworking.send(packet);
    }
}
