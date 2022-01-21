package me.felix.tired.bridge.rendering.clickgui;

import me.felix.tired.bridge.rendering.clickgui.layers.Layer;
import me.felix.tired.bridge.util.RenderUtil;
import me.felix.tired.main.Main;
import me.felix.tired.main.ThreadGetter;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.MathHelper;
import tired.jdk.api.Tired;
import tired.jdk.api.abstracts.Module;
import tired.jdk.intern.hooks.FontHook;
import tired.jdk.intern.hooks.MCHook;

import java.util.ArrayList;

public class Panel extends Layer implements MCHook, FontHook {

    private final Module.Category moduleCategory;

    private int x, y, dragX, dragY;

    private boolean dragging, extended;

    public int mouseX, mouseY;

    private final ArrayList<ModuleRendering> moduleRenderingArrayList;

    public Panel(int x, int y, Module.Category moduleCategory) {

        this.x = x;
        this.y = y;
        this.moduleCategory = moduleCategory;
        this.moduleRenderingArrayList = new ArrayList<>();

        for (Module module : Tired.INSTANCE.getModules()) {
            if (module.getCategory() != this.moduleCategory)
                continue;
            moduleRenderingArrayList.add(new ModuleRendering(module));
        }

    }

    @Override
    public void updateLayer(int mouseX, int mouseY) {

        this.mouseX = mouseX;
        this.mouseY = mouseY;
        this.mouseX /= ModuleRendering.scaleFactor;
        this.mouseY /= ModuleRendering.scaleFactor;
        
        for (ModuleRendering render : moduleRenderingArrayList) {
            render.updateLayer(mouseX, mouseY);
        }
        if (dragging) {
            this.x = mouseX + dragX;
            this.y = mouseY + dragY;
        }

    }

    //Font layer, rendering fonts over rect
    @Override
    public void renderLayer1() {

        final String categoryText = moduleCategory.getDisplayName();

        Main.fontLoader.bold.drawString(categoryText, Clickable.calculateMiddle(categoryText, Main.fontLoader.bold, x, Clickable.getWidth()), y + 4, -1);
        if (extended) {
            int yAdditional = 0;
            for (ModuleRendering render : moduleRenderingArrayList) {

                render.renderLayer1(mouseX, mouseY, x, y + 20 + yAdditional);
                yAdditional +=  Math.round (Clickable.height);
            }
        }
    }

    //rectangle Layer
    @Override
    public void renderLayer2() {

        RenderUtil.drawRoundedRectangle(x, y, x + Clickable.getWidth(), y + Clickable.getHeight(), 2, Integer.MIN_VALUE);

        if (extended) {
            int yAdditional = 0;
            for (ModuleRendering render : moduleRenderingArrayList) {
                render.renderLayer2(mouseX, mouseY, x, y + 20 + yAdditional);

                yAdditional += 20;

            }
        }

    }

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        mouseX /= ModuleRendering.scaleFactor;
        mouseY /= ModuleRendering.scaleFactor;
        boolean mouseOver = (Clickable.isOver(x, y, (int) Clickable.getWidth(), (int) Clickable.getHeight(), mouseX, mouseY));

        this.moduleRenderingArrayList.forEach(moduleButton -> moduleButton.mouseClicked(mouseButton));

        if (mouseOver) {
            if (mouseButton == 0) {
                this.dragX = x - mouseX;
                this.dragY = y - mouseY;
                this.dragging = true;
            } else if (mouseButton == 1) {
                this.extended = !extended;
            }

        }
    }

    public void mouseReleased() {
        this.dragging = false;
    }

}
