package me.felix.tired.bridge.util;

import lombok.experimental.UtilityClass;
import me.felix.tired.bridge.shader.BlurShader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;

@UtilityClass
public class BlurHelper {

    private BlurShader blurShader;

    public BlurShader getBlurShader(int radius) {

        if (blurShader == null) {
            blurShader = new BlurShader(radius);
        }
        if (blurShader.radius() != 0) {
            blurShader.radius(radius);
        }
        return blurShader;
    }

    public static void startBlur() {
        StencilUtil.initStencilToWrite();
    }

    public void stopBlur() {
        StencilUtil.readStencilBuffer(1);
        getBlurShader(22).blur();

        StencilUtil.uninitStencilBuffer();
        GlStateManager.enableBlend();
        Minecraft.getMinecraft().entityRenderer.setupOverlayRendering();
    }

    public void stopBlur(int radius) {
        StencilUtil.readStencilBuffer(1);
        getBlurShader(radius).blur();
        StencilUtil.uninitStencilBuffer();


    }

}
