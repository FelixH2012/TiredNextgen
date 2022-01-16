package me.felix.tired.bridge.util;

import lombok.experimental.UtilityClass;
import me.felix.tired.bridge.hooks.MCHook;
import net.minecraft.client.gui.Gui;

import java.awt.*;

@UtilityClass
public class RenderUtil implements MCHook {

    public void drawRectangle(final double x, final double y, final double width, final double height, final int color) {
        Gui.drawRect(x, y, width, height, color);
    }


    //@net.minecraft.client.gui.Gui
    public Color convertIntToColor(int color) {

        final float alpha = (float) (color >> 24 & 255) / 255.0F, red = (float) (color >> 16 & 255) / 255.0F, green = (float) (color >> 8 & 255) / 255.0F, blue = (float) (color & 255) / 255.0F;

        return new Color(red, green, blue, alpha);

    }

}
