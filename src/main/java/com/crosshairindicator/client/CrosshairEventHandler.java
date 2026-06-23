package com.crosshairindicator.client;

import com.crosshairindicator.client.renderer.CrosshairRenderer;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class CrosshairEventHandler {

    private final CrosshairRenderer renderer = new CrosshairRenderer();

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        while (KeyBindings.TOGGLE_KEY.consumeClick()) {
            CrosshairRenderer.toggleEnabled();
            Minecraft mc = Minecraft.getInstance();
            if (mc.player != null) {
                mc.player.displayClientMessage(
                    new net.minecraft.util.text.StringTextComponent(
                        CrosshairRenderer.isEnabled()
                            ? "\u00a7aCrosshair Indicator ON"
                            : "\u00a7cCrosshair Indicator OFF"
                    ),
                    true
                );
            }
        }
    }

    @SubscribeEvent
    public void onRenderOverlay(RenderGameOverlayEvent.Post event) {
        if (event.getType() != RenderGameOverlayEvent.ElementType.CROSSHAIRS) return;

        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.level == null) return;
        if (mc.options.hideGui) return;

        renderer.render(event.getMatrixStack(), mc, event);
    }
}
