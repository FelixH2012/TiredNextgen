package me.felix.tired.bridge.util;

import lombok.experimental.UtilityClass;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;
import tired.jdk.intern.hooks.MCHook;
import static org.lwjgl.opengl.GL11.*;
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

    public void glColor(int hex) {
        float alpha = (hex >> 24 & 0xFF) / 255.0F;
        float red = (hex >> 16 & 0xFF) / 255.0F;
        float green = (hex >> 8 & 0xFF) / 255.0F;
        float blue = (hex & 0xFF) / 255.0F;
        GL11.glColor4f(red, green, blue, alpha);
    }


    public void drawRoundedRectangle(double left, double top, double right, double bottom, double radius, int color) {
        glScaled(0.5D, 0.5D, 0.5D);
        left *= 2.0D;
        top *= 2.0D;
        right *= 2.0D;
        bottom *= 2.0D;
        glDisable(GL_TEXTURE_2D);
        glEnable(GL_LINE_SMOOTH);
        GlStateManager.enableBlend();
        glColor(color);
        glBegin(9);

        int i;
        for (i = 0; i <= 90; i += 1)
            glVertex2d(left + radius + Math.sin(i * Math.PI / 180.0D) * radius * -1.0D, top + radius + Math.cos(i * Math.PI / 180.0D) * radius * -1.0D);
        for (i = 90; i <= 180; i += 1)
            glVertex2d(left + radius + Math.sin(i * Math.PI / 180.0D) * radius * -1.0D, bottom - radius + Math.cos(i * Math.PI / 180.0D) * radius * -1.0D);
        for (i = 0; i <= 90; i += 1)
            glVertex2d(right - radius + Math.sin(i * Math.PI / 180.0D) * radius, bottom - radius + Math.cos(i * Math.PI / 180.0D) * radius);
        for (i = 90; i <= 180; i += 1)
            glVertex2d(right - radius + Math.sin(i * Math.PI / 180.0D) * radius, top + radius + Math.cos(i * Math.PI / 180.0D) * radius);
        glEnd();
        glEnable(GL_TEXTURE_2D);
        glScaled(2.0D, 2.0D, 2.0D);
        glColor4d(1, 1, 1, 1);


    }

}
