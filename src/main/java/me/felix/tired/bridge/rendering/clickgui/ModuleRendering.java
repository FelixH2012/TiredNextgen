package me.felix.tired.bridge.rendering.clickgui;

import me.felix.tired.bridge.rendering.clickgui.layers.Layer;
import me.felix.tired.bridge.util.RenderUtil;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import tired.jdk.api.abstracts.Module;
import tired.jdk.intern.hooks.FontHook;
import tired.jdk.intern.hooks.MCHook;

import java.awt.*;

public class ModuleRendering extends Layer implements MCHook, FontHook {

    private final Module module;

    public double x, y;

    private boolean hover;

    public ModuleRendering(final Module mod) {
        this.module = mod;
    }

    @Override
    public void updateLayer(int mouseX, int mouseY) {

        hover = Clickable.isOver((int) x, (int) y, (int) Clickable.getWidth(), (int) Clickable.getHeight(), mouseX, mouseY);

        super.updateLayer(mouseX, mouseY);
    }

    @Override
    public void renderLayer1(int mouseX, int mouseY, int x, int y) {
        this.x = x;
        this.y = y;
        //font
        {
            fontRenderer.drawString(module.getName(), Clickable.calculateMiddle(module.getName(), fontRenderer, x, Clickable.getWidth()), y + 5, -1);
        }
        super.renderLayer1(mouseX, mouseY, x, y);
    }

    @Override
    public void renderLayer2(int mouseX, int mouseY, int x, int y) {
        //rect

        GlStateManager.disableBlend();
        GlStateManager.resetColor();
        {
            RenderUtil.drawRoundedRectangle(x, y, x + Clickable.getWidth(), y + Clickable.getHeight(), 0, Integer.MIN_VALUE);

            if (hover)
                RenderUtil.drawRoundedRectangle(x, y, x + Clickable.getWidth(), y + Clickable.getHeight(), 0, Integer.MIN_VALUE);

            if (module.isToggled()) {
                RenderUtil.drawRoundedRectangle(x, y, x + Clickable.getWidth(), y + Clickable.getHeight(), 0, new Color(31, 170, 234, 122).getRGB());
            }

        }

        super.renderLayer2(mouseX, mouseY, x, y);
    }

    public void mouseClicked(int mouseButton) {
        if (hover) {
            if (mouseButton == 0) {
                module.setState(!module.isToggled());
            }
        }

    }

}
