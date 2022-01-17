package me.felix.tired.bridge.rendering.clickgui;

import me.felix.tired.bridge.rendering.clickgui.layers.Layer;
import net.minecraft.client.gui.Gui;
import tired.jdk.api.abstracts.Module;
import tired.jdk.intern.hooks.FontHook;
import tired.jdk.intern.hooks.MCHook;

public class Panel extends Layer implements MCHook, FontHook {

    private final Module.Category moduleCategory;

    private int x, y, dragX, dragY;

    private boolean dragging;

    public Panel(int x, int y, Module.Category moduleCategory) {

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

        final String categoryText = moduleCategory.getDisplayName();

        fontRenderer.drawStringWithShadow(categoryText, Clickable.calculateMiddle(categoryText, fontRenderer, x, Clickable.getWidth()), y + 5, -1);

    }

    //rectangle Layer
    @Override
    public void renderLayer2() {

        Gui.drawRect(x, y, x + Clickable.getWidth(), y + Clickable.getHeight(), Integer.MIN_VALUE);

    }

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        boolean mouseOver = (Clickable.isOver(x, y, (int) Clickable.getWidth(), (int) Clickable.getHeight(), mouseX, mouseY));

        if (mouseOver) {
            if (mouseButton == 0) {
                this.dragX = x - mouseX;
                this.dragY = y - mouseY;
                this.dragging = true;
            }
        }
    }

    public void mouseReleased() {
        this.dragging = false;
    }

}
