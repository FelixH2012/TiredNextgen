package me.felix.tired.threads;

import me.felix.tired.bridge.rendering.clickgui.ClickGUIRenderer;
import me.felix.tired.main.Main;
import tired.jdk.api.Logger;

public class MainThread extends Thread {

    @Override
    public void run() {
        Logger.INSTANCE.doLog("Initialized " + Main.NAME, Logger.Type.CONSOLE);
        Logger.INSTANCE.doLog("Tired Coded By Tired coding team, see more on: https://Tired-client.de/", Logger.Type.CONSOLE);
        Logger.INSTANCE.doLog("Loaded fonts.", Logger.Type.CONSOLE);
        super.run();
    }
}
