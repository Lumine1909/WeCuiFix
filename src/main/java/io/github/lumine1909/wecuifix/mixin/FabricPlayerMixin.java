package io.github.lumine1909.wecuifix.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.sk89q.worldedit.fabric.FabricPlayer;
import com.sk89q.worldedit.internal.cui.CUIEvent;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.level.ServerPlayer;
import org.enginehub.worldeditcui.protocol.CUIPacket;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(FabricPlayer.class)
public class FabricPlayerMixin {

    @Shadow
    @Final
    private ServerPlayer player;

    @WrapMethod(
        method = "dispatchCUIEvent",
        remap = false
    )
    private void dispatchCUIEvent(CUIEvent event, Operation<Void> original) {
        ServerPlayNetworking.send(this.player, new CUIPacket(event.getTypeId(), event.getParameters()));
    }
}