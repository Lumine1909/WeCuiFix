package io.github.lumine1909.wecuifix.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import org.enginehub.worldeditcui.fabric.network.FabricCUIPacketRegistration;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(FabricCUIPacketRegistration.class)
public class FabricCuiPacketRegMixin {

    @WrapMethod(
        method = {"onInitialize"},
        remap = false
    )
    public void onInitialize(Operation<Void> original) {
    }
}
