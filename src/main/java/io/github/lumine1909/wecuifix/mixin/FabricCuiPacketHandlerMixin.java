package io.github.lumine1909.wecuifix.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.sk89q.worldedit.fabric.net.handler.WECUIPacketHandler;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import org.enginehub.worldeditcui.fabric.network.FabricCUIPacketHandler;
import org.enginehub.worldeditcui.protocol.CUIPacket;
import org.enginehub.worldeditcui.protocol.CUIPacketHandler;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Set;
import java.util.function.BiConsumer;

import static io.github.lumine1909.wecuifix.WeCuiFix.decode;

@Mixin(FabricCUIPacketHandler.class)
public abstract class FabricCuiPacketHandlerMixin {

    @Final
    @Shadow(remap = false)
    private static Set<BiConsumer<CUIPacket, CUIPacketHandler.PacketContext>> CLIENTBOUND_HANDLERS;

    @WrapMethod(
        method = {"registerClient"},
        remap = false
    )
    private static void registerClient(Operation<Void> original) {
        ClientPlayNetworking.registerGlobalReceiver(WECUIPacketHandler.CuiPacket.TYPE, (packet, ctx) -> {
            CUIPacketHandler.PacketContext cuiCtx = new CUIPacketHandler.PacketContext(ctx.player(), ctx.client());
            CUIPacket pkt = decode(packet.text());
            for (BiConsumer<CUIPacket, CUIPacketHandler.PacketContext> handler : CLIENTBOUND_HANDLERS) {
                handler.accept(pkt, cuiCtx);
            }
        });
    }
}