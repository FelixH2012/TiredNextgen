package me.felix.tired.bridge.rendering.clickgui;

import lombok.Getter;
import lombok.experimental.UtilityClass;
import me.felix.tired.bridge.font.FontRendering;
import me.felix.tired.bridge.hooks.FontHook;
import me.felix.tired.bridge.hooks.MCHook;

@UtilityClass
public class Clickable implements MCHook, FontHook {

    @Getter
    private final double width = 120, height = 20;

    public int calculateMiddle(String text, FontRendering fontRenderer, double x, double widht) {
        return (int) ((float) (x + widht) - (fontRenderer.getStringWidth(text) / 2f) - (float) widht / 2);
    }

    public boolean isOver(int x, int y, int width, int height, int mouseX, int mouseY) {
        return mouseX > x && mouseX < x + width && mouseY > y && mouseY < y + height;
    }

}
