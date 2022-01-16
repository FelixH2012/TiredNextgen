package me.felix.tired.bridge.rendering.clickgui;

import me.felix.tired.bridge.hooks.FontHook;
import me.felix.tired.bridge.hooks.MCHook;
import me.felix.tired.bridge.rendering.clickgui.layers.Layer;
import me.felix.tired.modification.ModuleCategory;
import net.minecraft.client.gui.Gui;

public class Panel extends Layer implements MCHook, FontHook {

    private final ModuleCategory moduleCategory;

    private int x, y, dragX, dragY;

    private boolean dragging;

    public Panel(int x, int y, ModuleCategory moduleCategory) {

        this.x = x;
        this.y = y;
        this.moduleCategory = moduleCategory;

    }

    @Override
    public void updateLayer(int mouseX, int mouseY) {

        if (dragging) {
            this.x = mouseX + dragX;
            this.y = mouseY + dragY;
        }

    }

    //Font layer, rendering fonts over rect
    @Override
    public void renderLayer1() {

        final String categoryText = moduleCategory.getCleanName();

        fontRenderer.drawStringWithShadow(categoryText, Clickable.calculateMiddle(categoryText, fontRenderer, x, Clickable.getWidth()), y - 2, -1);

    }

    //rectangle Layer
    @Override
    public void renderLayer2() {

        Gui.drawRect(x, y, x + Clickable.getWidth(), y + Clickable.getHeight(), Integer.MIN_VALUE);

    }
}
