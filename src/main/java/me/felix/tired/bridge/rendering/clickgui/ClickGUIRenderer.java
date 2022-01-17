package me.felix.tired.bridge.rendering.clickgui;

import me.felix.tired.modification.ModuleCategory;
import net.minecraft.client.gui.GuiScreen;

import java.io.IOException;
import java.util.ArrayList;

public class ClickGUIRenderer extends GuiScreen {

    private final ArrayList<Panel> panels;

    public ClickGUIRenderer() {
        this.panels = new ArrayList<>();
        int x = 0;
        for (ModuleCategory category : ModuleCategory.values()) {
            panels.add(new Panel(20 + x, 30, category));
            x += 40;
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        for (Panel panel : panels) {
            panel.updateLayer(mouseX, mouseY);
            panel.renderLayer2();
            panel.renderLayer1();
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
