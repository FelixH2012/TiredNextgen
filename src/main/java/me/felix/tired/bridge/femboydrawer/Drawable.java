package me.felix.tired.bridge.femboydrawer;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import tired.jdk.api.Logger;
import tired.jdk.intern.hooks.MCHook;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Drawable extends GuiScreen implements MCHook {

    List<String> results = new ArrayList<String>();

    public Drawable() {
    }

    @Override
    public void initGui() {

//If this pathname does not denote a directory, then listFiles() returns null.
        File[] files = new File("assets/minecraft/femboys").listFiles();
        if (files == null) return;
        for (File file : files) {
            if (file.isFile()) {
                results.add(file.getName());
            }
        }

        super.initGui();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {


        int yAdd = 0;
        for (String string : results) {

            try {
                if (ClassLoader.getSystemClassLoader().getResourceAsStream("assets/minecraft/femboys/" + string) != null) {
                    final String endAble = String.valueOf(ClassLoader.getSystemClassLoader().getResourceAsStream("assets/minecraft/femboys/" + string));
                    if (endAble.endsWith(".png")) return;
                    if (string.isEmpty() || results.isEmpty()) return;
                    final InputStream is = Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResourceAsStream("assets/minecraft/femboys/" + string));

                    if (is.available() == 0) return;
                    final BufferedImage iis = ImageIO.read(is);

                    ImageIO.setUseCache(false);
                    final ExternalImageDrawer externalImage = new ExternalImageDrawer(iis);
                    externalImage.draw(20, 20 + yAdd, 120, 120);
                    iis.flush();
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            yAdd += 70;

        }
        super.drawScreen(mouseX, mouseY, partialTicks);
    }


}
