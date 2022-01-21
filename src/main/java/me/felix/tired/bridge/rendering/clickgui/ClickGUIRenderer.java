package me.felix.tired.bridge.rendering.clickgui;

import me.felix.tired.bridge.util.BlurHelper;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;
import tired.jdk.api.Logger;
import tired.jdk.api.Tired;
import tired.jdk.api.abstracts.Module;

import java.io.IOException;
import java.util.ArrayList;

public class ClickGUIRenderer extends GuiScreen {

    private ArrayList<Panel> panels;

    public void add() {
        this.panels = new ArrayList<>();
        int x = 0;
        for (Module.Category category : Module.Category.values()) {
            panels.add(new Panel(20 + x, 30, category));
            x += 120;
        }


    }


    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
       float scaleFactor = (float) ModuleRendering.scaleFactor;

       if (scaleFactor == 1.0) {
           for (Panel panel : panels) {
               panel.renderLayer1();
           }
           BlurHelper.startBlur();
           for (Panel panel : panels) {
               panel.updateLayer(mouseX, mouseY);
               panel.renderLayer2();
           }
           BlurHelper.stopBlur(14);
           for (Panel panel : panels) {
               panel.renderLayer2();
           }
           for (Panel panel : panels) {
               panel.renderLayer1();
           }
       } else {
           GL11.glPushMatrix();
           GL11.glScalef(scaleFactor, scaleFactor, scaleFactor);
           for (Panel panel : panels) {
               panel.updateLayer(mouseX, mouseY);
           }
           for (Panel panel : panels) {
               panel.renderLayer2();
           }
           for (Panel panel : panels) {
               panel.renderLayer1();
           }
           GL11.glPopMatrix();
       }
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        for (Panel panel : panels) {
            panel.mouseReleased();
        }
        super.mouseReleased(mouseX, mouseY, state);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        for (Panel panel : panels) {
            panel.mouseClicked(mouseX, mouseY, mouseButton);
        }
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }
}
