package me.felix.tired.bridge.femboydrawer;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import tired.jdk.intern.hooks.MCHook;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Drawable extends GuiScreen implements MCHook {

    List<String> results = new ArrayList<String>();

    public Drawable() {
    }

    @Override
    public void initGui() {
        FemboyScraper.getAndDownloadPosts(250);
        File[] files = new File("assets/minecraft/resTired").listFiles();
//If this pathname does not denote a directory, then listFiles() returns null.

        assert files != null;
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

            MC.getTextureManager().bindTexture(new ResourceLocation("assets/minecraft/resTired" + string));
            Gui.drawModalRectWithCustomSizedTexture(120, 120 + yAdd, 50, 50, 50, 50, 50, 50);

            yAdd += 70;

        }

        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
