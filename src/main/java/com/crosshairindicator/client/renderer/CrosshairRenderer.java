package com.crosshairindicator.client.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class CrosshairRenderer {

    private static final ResourceLocation CROSSHAIR_TEXTURE =
        new ResourceLocation("crosshairindicator", "textures/gui/crosshair.png");

    private static boolean enabled = true;

    public static boolean isEnabled() {
        return enabled;
    }

    public static void toggleEnabled() {
        enabled = !enabled;
    }

    public void render(MatrixStack matrixStack, Minecraft mc, RenderGameOverlayEvent.Post event) {
        if (!enabled) return;

        // Only show custom crosshair when aiming at an entity (the key feature)
        if (mc.crosshairPickEntity == null) return;

        int screenWidth = event.getWindow().getGuiScaledWidth();
        int screenHeight = event.getWindow().getGuiScaledHeight();

        // Center 16x16 crosshair on screen
        int x = screenWidth / 2 - 8;
        int y = screenHeight / 2 - 8;

        // Exact same blend mode as vanilla crosshair — inverted color
        RenderSystem.enableBlend();
        RenderSystem.blendFuncSeparate(
            GlStateManager.SourceFactor.ONE_MINUS_DST_COLOR,
            GlStateManager.DestFactor.ONE_MINUS_SRC_COLOR,
            GlStateManager.SourceFactor.ONE,
            GlStateManager.DestFactor.ZERO
        );
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);

        mc.getTextureManager().bind(CROSSHAIR_TEXTURE);

        // Draw 16x16 from top-left of the 256x256 texture (UV 0,0)
        AbstractGui.blit(matrixStack, x, y, 0, 0, 16, 16, 256, 256);

        RenderSystem.disableBlend();
        RenderSystem.defaultBlendFunc();
    }
}
