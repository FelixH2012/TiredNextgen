package me.felix.tired.threads;

import me.felix.tired.main.Main;
import me.felix.tired.logger.Logger;
import tired.jdk.api.Logger;

public class MainThread extends Thread {

    @Override
    public void run() {
        Logger.INSTANCE.doLog("Initialized " + Main.NAME, Logger.Type.CONSOLE);
        super.run();
    }
}
